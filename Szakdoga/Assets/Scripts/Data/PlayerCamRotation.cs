using UnityEngine;

namespace Data
{
    public class PlayerCamRotation : MonoBehaviour
    {
        private static PlayerCamRotation _instance;

        public static PlayerCamRotation Instance
        {
            get
            {
                if (_instance == null)
                    Debug.LogError("Can't instantiate object to get player camera rotation");
                return _instance;
            }
        }

        private void Awake()
        {
            _instance = this;
        }

        public Vector3 ForwardVector => transform.forward;

        public Vector3 EulerAngles => transform.eulerAngles;

        public Vector3 CameraPosition => transform.position;
    }
}
