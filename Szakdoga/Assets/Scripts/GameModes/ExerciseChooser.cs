using System.Collections.Generic;
using Controllers;
using Data;
using Enums;
using Managers;
using TMPro;
using UnityEngine;

namespace GameModes
{
    public class ExerciseChooser : MonoBehaviour
    {
        private GameManager _game;
        private UIManager _ui;
        private List<GameObject> _boxArray;
        private ExerciseDictionary _exercises;

        public int ChosenExercise { get; private set; }

        private int _currentX = 0;
        private int _currentY = 0;

        private const int maxX = 10;
        private const int maxY = 5;

        private const float XIncrement = 6.0f;
        private const float YIncrement = -6.0f;

        private readonly Vector3 _startPosition = new(-30.0f, 80.0f, 50.0f);

        public GameObject exerciseChooserBoxPrefab;

        private static ExerciseChooser _instance;

        public static ExerciseChooser Instance
        {
            get
            {
                if (_instance == null)
                    Debug.LogError("Exercise chooser singleton not instantiated!");
                return _instance;
            }
        }

        private void Awake()
        {
            _instance = this;
        }

        private void Start()
        {
            _game = GameManager.Instance;
            _ui = UIManager.Instance;
            _exercises = ExerciseDictionary.Instance;
            _boxArray = new List<GameObject>();
        }

        private void Update()
        {
            if (_game.State != GameState.ExerciseMenu) return;
            if (_boxArray.Count == 0) SpawnBoxes();
            if (!MainController.Instance.IsMainInput()) return;
            Ray ray = Camera.main.ViewportPointToRay(new Vector3(0.5f, 0.5f, 0f));
            Vector3 rayDirection = ray.direction * 100f;

            if (!Physics.Raycast(ray.origin, rayDirection, out RaycastHit hit)) return;
            GameObject o = hit.collider.gameObject;
            _ui.debug.RaycastDebugText = "Ray collided with " + o.name;
            
            if (o.name != "ExerciseClickBox") return;
            TextMeshPro tmp = (TextMeshPro)o.transform.parent.GetChild(0).GetComponentInChildren(typeof(TextMeshPro));
            ChosenExercise = int.Parse(tmp.text);
            Debug.Log("Exercise chosen: " + ChosenExercise);
            DespawnBoxes();
            _boxArray = new List<GameObject>();
            _game.State = GameState.BorderCalculation;

        }

        private void DespawnBoxes()
        {
            foreach (GameObject obj in _boxArray)
            {
                _game.DestroyObject(obj);
            }
        }

        private void SpawnBoxes()
        {
            int exerciseNum = _exercises.NumberOfExercises;
            if (exerciseNum == 0)
            {
                _game.State = GameState.Menu;
                return;
            }

            for (int i = 1; i <= exerciseNum; i++)
            {
                GameObject obj = _game.SpawnObject(exerciseChooserBoxPrefab, NextVector());
                TextMeshPro tmp = (TextMeshPro)obj.transform.GetChild(0).GetComponentInChildren(typeof(TextMeshPro));
                tmp.text = i.ToString();
                _boxArray.Add(obj);
            }

            _currentX = 0;
            _currentY = 0;
        }

        private Vector3 NextVector()
        {
            if (_currentX < maxX && _currentY < maxY)
            {
                Vector3 nextPosition = new Vector3(
                    _startPosition.x + _currentX * XIncrement,
                    _startPosition.y + _currentY * YIncrement,
                    _startPosition.z
                );
                _currentX++;

                if (_currentX < maxX) return nextPosition;
                _currentX = 0;
                _currentY++;

                return nextPosition;
            }

            _currentX = 0;
            _currentY = 0;
            return Vector3.zero;
        }
    }
}