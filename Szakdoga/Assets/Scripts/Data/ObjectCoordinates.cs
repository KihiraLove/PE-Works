using UnityEngine;

namespace Data
{
    public class ObjectCoordinates : MonoBehaviour
    {
        //Singleton implementation
        private static ObjectCoordinates _instance;
        public static ObjectCoordinates Instance
        {
            get
            {
                if(_instance == null)
                    Debug.LogError("Can't instantiate object to collect coordinates");
                return _instance;
            }
        }
        private void Awake()
        {
            _instance = this;
        }
        
        public GameObject menuShift;

        public GameObject shiftForward;

        public GameObject shiftUp;

        public Vector3 MenuShiftPosition => menuShift.transform.position;
        public Vector3 BaseLeftBorder => new(-50, 60, 0);
        public Vector3 BaseRightBorder => new(50, 60, 0);
        public Vector3 BaseUpperBorder => new(0, 110, 0);
        public Vector3 BaseLowerBorder => new(0, 10, 0);

        public float SpawnDistanceFromPlayer => shiftForward.transform.localPosition.z + menuShift.transform.localPosition.z;
    }
}
