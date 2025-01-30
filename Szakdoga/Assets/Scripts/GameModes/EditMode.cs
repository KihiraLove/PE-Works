using System.Collections.Generic;
using Controllers;
using Data;
using Enums;
using Managers;
using UnityEngine;
using Managers.SubManagers;

namespace GameModes
{
    public class EditMode : MonoBehaviour
    {
        private List<GameObject> _objects;
        private GameManager _game;
        private UIManager _ui;
        private PlayerCamRotation _camRotation;
        private Border _border;
        private ExerciseDictionary _exercises;
        private FileHandler _fileHandler;
        private int consumableFrames = 10;

        public GameObject spherePrefab;
        public GameObject exitEditModeButtonPrefab;

        private GameObject _exitEditModeButton;
        private void Start()
        {
            _game = GameManager.Instance;
            _camRotation = PlayerCamRotation.Instance;
            _border = Border.Instance;
            _ui = UIManager.Instance;
            _exercises = ExerciseDictionary.Instance;
            _objects = new List<GameObject>();
            _fileHandler = new FileHandler();
        }

        private void Update()
        {
            if (_game.State != GameState.EditMode) return;
            if (!_exitEditModeButton) _exitEditModeButton = _game.SpawnObject(exitEditModeButtonPrefab, new Vector3(30, 30, 30));
            _ui.debug.DebugText2 = consumableFrames.ToString();
            if (consumableFrames != 0)
            {
                consumableFrames--;
                return;
            }
            if(!MainController.Instance.IsMainInput()) return;
            Ray ray = Camera.main.ViewportPointToRay(new Vector3(0.5f, 0.5f, 0f));
            Vector3 rayDirection = ray.direction * 100f;
            if (Physics.Raycast(ray.origin, rayDirection, out RaycastHit hit) && hit.collider.gameObject.name == "ExitEditModeBox")
            {
                GameObject o = hit.collider.gameObject;
                _ui.debug.RaycastDebugText = "Ray collided with " + o.name;
                _game.DestroyObject(_exitEditModeButton);
                List<Vector3> vectors = new List<Vector3>();
                foreach (GameObject obj in _objects)
                {
                    vectors.Add(obj.transform.position);
                    _game.DestroyObject(obj);
                }
                _exercises.AddExercise(vectors);
                _fileHandler.SaveVectors(vectors);
                _objects = new List<GameObject>();
                _game.State = GameState.Menu;
                consumableFrames = 10;
                return;
            }
            Vector3 position = PlayerCamRotation.Instance.CameraPosition + PlayerCamRotation.Instance.ForwardVector * ObjectCoordinates.Instance.SpawnDistanceFromPlayer;
            _objects.Add(_game.SpawnObject(spherePrefab, position));
        }
    }
}