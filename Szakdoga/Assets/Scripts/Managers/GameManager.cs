using Data;
using Enums;
using UnityEngine;

namespace Managers
{
    public class GameManager : MonoBehaviour
    {
        private ObjectCoordinates _objectCoordinates;
        private PlayerCamRotation _playerCamRotation;
        private Border _border;
        private UIManager _ui;
        private GameState _state;
                
        public GameObject shiftForward;     
        public GameObject menuShift;
        
        public GameObject debugPrefab;
        public GameObject menuPrefab;

        private GameObject _menu;
        private GameObject _debugCapsules;

        private static GameManager _instance;

        public static GameManager Instance
        {
            get
            {
                if (_instance == null)
                    Debug.LogError("Game Manager singleton not instantiated!");
                return _instance;
            }
        }

        private void Awake()
        {
            _instance = this;
        }
        
        void Start()
        {
            _objectCoordinates = ObjectCoordinates.Instance;
            _playerCamRotation = PlayerCamRotation.Instance;
            _border = Border.Instance;
            State = GameState.Menu;
            _ui = UIManager.Instance;
            
            _menu = Instantiate(menuPrefab, menuShift.transform.position, Quaternion.identity);
            SwitchDebugMode();
        }

        private void Update()
        {
            if (State == GameState.Menu && !_menu)
            {
                _menu = Instantiate(menuPrefab, menuShift.transform.position, Quaternion.identity);
            }
            else if (State != GameState.Menu && _menu)
            {
                Destroy(_menu);
            }
        }

        public GameState State
        {
            get => _state;
            set
            {
                _state = value;
                Debug.Log(_state);
            }
        }

        public void SwitchDebugMode()
        {
            if (_ui.debug.Enabled)
            {
                DestroyObject(_debugCapsules);
            }
            else
            {
                _debugCapsules = SpawnObject(debugPrefab, shiftForward.transform.position);
            }
            _ui.debug.SwitchDebugMode();
        }

        public GameObject SpawnObject(GameObject original, Vector3 position)
        {
            return Instantiate(original, position, Quaternion.identity);
        }

        public void DestroyObject(GameObject obj)
        {
            Destroy(obj);
        }
    }
}
