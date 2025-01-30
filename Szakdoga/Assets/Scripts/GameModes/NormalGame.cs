using System;
using System.Collections.Generic;
using System.Linq;
using Data;
using Enums;
using Managers;
using UnityEngine;

namespace GameModes
{
    public class NormalGame : MonoBehaviour
    {

        private List<Vector3> _positions;
        private GameObject _currentObject;
        private ExerciseDictionary _exercises;
        private GameManager _game;
        private UIManager _ui;
        private ExerciseChooser _exerciseChooser;
        private bool _isExerciseLoaded = false;
        private int _index = 0;

        public GameObject spherePrefab;

        private void Start()
        {
            _exercises = ExerciseDictionary.Instance;
            _game = GameManager.Instance;
            _ui = UIManager.Instance;
            _exerciseChooser = ExerciseChooser.Instance;
            _positions = new List<Vector3>();
        }

        private void Update()
        {
            if (_game.State != GameState.InGame) return;
            if (!_isExerciseLoaded)
            {
                LoadExercise();
                _currentObject = _game.SpawnObject(spherePrefab, GetNextVector().Value);
            }
            
            Ray ray = Camera.main.ViewportPointToRay(new Vector3(0.5f, 0.5f, 0f));

            if (!Physics.Raycast(ray.origin, ray.direction * 100f, out RaycastHit hit)) return;
            GameObject o = hit.collider.gameObject;
            _ui.debug.RaycastDebugText = "Ray collided with " + o.name;
            if(o.name == "Sphere(Clone)")
            {
                NextObject();
            }
        }

        private void NextObject()
        {
            _game.DestroyObject(_currentObject);
            Vector3? nextPos = GetNextVector();
            if (nextPos.HasValue)
            {
                _currentObject = _game.SpawnObject(spherePrefab, nextPos.Value);
                return;
            }
            EndExercise();
        }

        private void EndExercise()
        {
            _isExerciseLoaded = false;
            _game.State = GameState.GameOver;
        }
        
        private Vector3? GetNextVector()
        {
            if (_positions == null || _index >= _positions.Count)
            {
                _index = 0;
                return null;
            }
            Vector3 nextVector = _positions[_index];
            _index++;
            return nextVector;
        }

        private void LoadExercise()
        {
            _positions = NormalizePositions(ScalePositionsToBorders(_exercises.GetExercise(_exerciseChooser.ChosenExercise)));
            Debug.Log("exercise loaded"); 
            _isExerciseLoaded = true;
        }

        private List<Vector3> NormalizePositions(List<Vector3> positions)
        {
            return positions.Select(
                position =>
                    transform.position + Vector3.Normalize(position - transform.position) * ObjectCoordinates.Instance.SpawnDistanceFromPlayer)
                .ToList();
        }

        private List<Vector3> ScalePositionsToBorders(List<Vector3> positions)
        {
            List<Vector3> scaledPositions = new List<Vector3>(positions);

            scaledPositions = ScalePositionsLeftAndRight(scaledPositions);
            scaledPositions = ScalePositionsUpAndDown(scaledPositions);
            
            return scaledPositions;
        }

        private List<Vector3> ScalePositionsLeftAndRight(List<Vector3> positions)
        {
            positions = ScalePositionsLeft(positions);
            positions = ScalePositionsRight(positions);
            return positions;
        }
        
        private List<Vector3> ScalePositionsUpAndDown(List<Vector3> positions)
        {
            positions = ScalePositionsUp(positions);
            positions = ScalePositionsDown(positions);
            return positions;
        }

        private List<Vector3> ScalePositionsRight(List<Vector3> positions)
        {
            ObjectCoordinates coords = ObjectCoordinates.Instance;
            Border border = Border.Instance;
            
            Vector3 player = transform.position;
            Vector3 origin = coords.MenuShiftPosition;

            Vector3 playerOriginVector = origin - player;
            Vector3 playerBaseBorderVector = coords.BaseRightBorder - player;
            Vector3 playerNewBorderVector = border.RightBorder - player;

            float alpha = Vector3.Angle(playerOriginVector, playerBaseBorderVector);
            float gamma = Vector3.Angle(playerOriginVector, playerNewBorderVector);

            for(int i = 0; i < positions.Count; i++)
            {
                if (!(positions[i].x > 0)) continue;
                
                Vector3 positionNormalizedToPlane = new Vector3(positions[i].x, 60, positions[i].z);
                Vector3 playerBaseObjectVector = positionNormalizedToPlane - player;
                
                float r = coords.SpawnDistanceFromPlayer;
                float beta = Vector3.Angle(playerOriginVector, playerBaseObjectVector);
                float ro = (gamma * (beta / alpha));
                
                ro = 90 - ro;
                double roRads = (Math.PI / 180) * ro;
                
                double x = r * Math.Cos(roRads);
                double z = r * Math.Sin(roRads);
            
                Vector3 newVector = new Vector3((float)x, positions[i].y, (float)z);
                positions[i] = newVector;
            }

            return positions;
        }

        private List<Vector3> ScalePositionsLeft(List<Vector3> positions)
        {
            ObjectCoordinates coords = ObjectCoordinates.Instance;
            Border border = Border.Instance;
            
            Vector3 player = transform.position;
            Vector3 origin = coords.MenuShiftPosition;

            Vector3 playerOriginVector = origin - player;
            Vector3 playerBaseBorderVector = coords.BaseLeftBorder - player;
            Vector3 playerNewBorderVector = border.LeftBorder - player;

            float alpha = Vector3.Angle(playerOriginVector, playerBaseBorderVector);
            float gamma = Vector3.Angle(playerOriginVector, playerNewBorderVector);
            
            for(int i = 0; i < positions.Count; i++)
            {
                if (!(positions[i].x < 0)) continue;
                
                Vector3 positionNormalizedToPlane = new Vector3(positions[i].x, 60, positions[i].z);
                Vector3 playerBaseObjectVector = positionNormalizedToPlane - player;
                
                float r = coords.SpawnDistanceFromPlayer;
                float beta = Vector3.Angle(playerOriginVector, playerBaseObjectVector);
                float ro = (gamma * (beta / alpha));
                double roRads = (Math.PI / 180) * ro;

                double z = r * Math.Cos(roRads);
                double x = r * Math.Sin(roRads) * -1;
            
                Vector3 newVector = new Vector3((float)x, positions[i].y, (float)z);
                positions[i] = newVector;
            }
            return positions;
        }

        private List<Vector3> ScalePositionsUp(List<Vector3> positions)
        {
            ObjectCoordinates coords = ObjectCoordinates.Instance;
            Border border = Border.Instance;
            
            Vector3 player = transform.position;
            Vector3 origin = coords.MenuShiftPosition;

            Vector3 playerOriginVector = origin - player;
            Vector3 playerBaseBorderVector = coords.BaseUpperBorder - player;
            Vector3 playerNewBorderVector = border.UpperBorder - player;

            float alpha = Vector3.Angle(playerOriginVector, playerBaseBorderVector);
            float gamma = Vector3.Angle(playerOriginVector, playerNewBorderVector);
            
            for(int i = 0; i < positions.Count; i++)
            {
                if (!(positions[i].y > 60)) continue;
                Vector3 positionNormalizedToPlane = new Vector3(0, positions[i].y, positions[i].z);
                Vector3 playerBaseObjectVector = positionNormalizedToPlane - player;
                
                float r = coords.SpawnDistanceFromPlayer;
                float beta = Vector3.Angle(playerOriginVector, playerBaseObjectVector);
                float ro = (gamma * (beta / alpha));
                double roRads = (Math.PI / 180) * ro;

                double z = r * Math.Cos(roRads); //* (Math.Clamp(Math.Abs(positions[i].y - 60), 15, 50) / 50);
                double y = r * Math.Sin(roRads) + 60;
            
                Vector3 newVector = new Vector3(positions[i].x, (float)y, (float)z);
                positions[i] = newVector;
            }

            return positions;
        }

        private List<Vector3> ScalePositionsDown(List<Vector3> positions)
        {
            ObjectCoordinates coords = ObjectCoordinates.Instance;
            Border border = Border.Instance;
            
            Vector3 player = transform.position;
            Vector3 origin = coords.MenuShiftPosition;

            Vector3 playerOriginVector = origin - player;
            Vector3 playerBaseBorderVector = coords.BaseLowerBorder - player;
            Vector3 playerNewBorderVector = border.LowerBorder - player;

            float alpha = Vector3.Angle(playerOriginVector, playerBaseBorderVector);
            float gamma = Vector3.Angle(playerOriginVector, playerNewBorderVector);
            
            for(int i = 0; i < positions.Count; i++)
            {
                if (!(positions[i].y < 60)) continue;
                Vector3 positionNormalizedToPlane = new Vector3(0, positions[i].y, positions[i].z);
                Vector3 playerBaseObjectVector = positionNormalizedToPlane - player;
                
                float r = coords.SpawnDistanceFromPlayer;
                float beta = Vector3.Angle(playerOriginVector, playerBaseObjectVector);
                float ro = (gamma * (beta / alpha));
                double roRads = (Math.PI / 180) * ro;

                double z = r * Math.Cos(roRads); // * (Math.Clamp(Math.Abs(positions[i].y - 60), 15, 50) / 50);
                double y = r * Math.Sin(roRads) * -1 + 60;
            
                Vector3 newVector = new Vector3(positions[i].x, (float)y, (float)z);
                positions[i] = newVector;
            }

            return positions;
        }
    }
}
