using System;
using Data;
using Managers.SubManagers;
using UnityEngine;
using UnityEngine.UI;

namespace Managers
{
    public class UIManager : MonoBehaviour
    {
        private PlayerCamRotation _playerCamRotation;
        private GameManager _gameManager;
        private Border _border;
        public DebugManager debug;
        public BorderHelper borderHelper;

        public Text debugText;
        public Text debugText2;
        public Text raycastDebugText;
        public Text borderHelperText;
        public GameObject debugSpherePrefab;
        
        private static UIManager _instance;
        public static UIManager Instance
        {
            get
            {
                if(_instance == null)
                    Debug.LogError("UI Manager not instantiated!");
                return _instance;
            }
        }

        private void Awake()
        {
            _instance = this;
        }

        // Start is called before the first frame update
        void Start()
        {
            _playerCamRotation = PlayerCamRotation.Instance;
            _gameManager = GameManager.Instance;
            _border = Border.Instance;
            debug = new DebugManager(debugText, debugText2, raycastDebugText, debugSpherePrefab);
            borderHelper = new BorderHelper(borderHelperText);
            borderHelper.ClearBorderText();
        }

        // Update is called once per frame
        void Update()
        {
            debug.CamRotationValues(_playerCamRotation.EulerAngles.y, _playerCamRotation.EulerAngles.x);
        }
    }
}
