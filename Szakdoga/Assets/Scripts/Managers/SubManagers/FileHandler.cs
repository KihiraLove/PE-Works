using System;
using System.Collections.Generic;
using UnityEngine;
using System.IO;
using System.Linq;
using System.Text;
using Data;

namespace Managers.SubManagers
{
    public class FileHandler
    {

        public string ExercisesPath => Application.dataPath + "\\Exercises";

        public void SaveVectors(List<Vector3> vectors)
        {
            int exerciseNumber = ExerciseDictionary.Instance.NumberOfExercises;
            string path = ExercisesPath + "\\" + exerciseNumber + ".json";
            string json = SerializeVectorListToJson(vectors);

            File.WriteAllText(path, json);
        }
        
        public string SerializeVectorListToJson(List<Vector3> vectorList)
        {
            StringBuilder sb = new StringBuilder();
            sb.Append("{\"Vector3\":[");

            for (int i = 0; i < vectorList.Count; i++)
            {
                Vector3 v = vectorList[i];
                sb.Append("{\"x\":");
                sb.Append(v.x.ToString("F3"));
                sb.Append(",\"y\":");
                sb.Append(v.y.ToString("F3"));
                sb.Append(",\"z\":");
                sb.Append(v.z.ToString("F3"));
                sb.Append("}");

                if (i < vectorList.Count - 1)
                {
                    sb.Append(",");
                }
            }

            sb.Append("]}");

            return sb.ToString();
        }

        public List<Vector3> DeserializeJsonToVectorList(string json)
        {
            int startIndex = json.IndexOf("[{", StringComparison.Ordinal) + 1;
            int endIndex = json.LastIndexOf("}]", StringComparison.Ordinal);

            string[] vectorJsonStrings = json.Substring(startIndex, endIndex - startIndex)
                .Split(new[] { "},{" }, StringSplitOptions.RemoveEmptyEntries);

            return (from vectorJson in vectorJsonStrings
                select vectorJson.Replace("{", "")
                    .Replace("}", "")
                    .Split(new[] { ',' }, StringSplitOptions.RemoveEmptyEntries)
                into components
                let x = float.Parse(components[0].Split(':')[1])
                let y = float.Parse(components[1].Split(':')[1])
                let z = float.Parse(components[2].Split(':')[1])
                select new Vector3(x, y, z)).ToList();
        }
    }
}