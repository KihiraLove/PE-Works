using System.Collections.Generic;
using System.IO;
using System.Linq;
using Managers.SubManagers;
using UnityEngine;

namespace Data
{
    public class ExerciseDictionary : MonoBehaviour
    {

        private List<List<Vector3>> _exercises;

        private static ExerciseDictionary _instance;

        public static ExerciseDictionary Instance
        {
            get
            {
                if (_instance == null)
                    Debug.LogError("Game Manager got sucked into the void!");
                return _instance;
            }
        }

        private void Awake()
        {
            _instance = this;
        }

        private void Start()
        {
            _exercises = new List<List<Vector3>>();
            InitializeDictionary();
        }

        private void InitializeDictionary()
        {
            FileHandler fileHandler = new FileHandler();
            string[] fileNames = Directory.GetFiles( fileHandler.ExercisesPath, "*.json");
            if (fileNames.Length == 0) return;
            foreach (string fileName in fileNames)
            {
                string json = File.ReadAllText(fileName);
                List<Vector3> exercise = fileHandler.DeserializeJsonToVectorList(json);
                AddExercise(exercise);
            }
            Debug.Log("There are " + _exercises.Count + " exercises loaded from JSON");
        }

        public List<Vector3> GetExercise(int exerciseNumber)
        {
            Debug.Log("Exercise index: " + (exerciseNumber - 1));
            return _exercises.ElementAt(exerciseNumber - 1);
        }

        public void AddExercise(List<Vector3> exercise)
        {
            _exercises.Add(exercise);
        }
        
        public int NumberOfExercises => _exercises.Count;
    }
}