using System;
using Enums;
using Managers;
using UnityEngine;

namespace Data
{
    public class Border : MonoBehaviour
    {
        private GameManager _game;
        private UIManager _ui;
        private float[] _borderRotationDegrees;
        private ObjectCoordinates _objectCoordinates;
        private PlayerCamRotation _playerCamRotation;
        private BorderFlag _borderFlag;
        private int _pop;
        private float _lastDegree;
        private bool _helperTextCalled;

        private Vector3 _rightBorder;
        private Vector3 _upperBorder;
        private Vector3 _lowerBorder;
        
        public int pop;

        private static Border _instance;

        public static Border Instance
        {
            get
            {
                if (_instance == null)
                    Debug.LogError("Border singleton not instantiated!");
                return _instance;
            }
        }

        private void Awake()
        {
            _instance = this;
        }
        
        // Start is called before the first frame update
        private void Start()
        {
            _borderRotationDegrees = new float[] { 0, 0, 0, 0 };
            _objectCoordinates = ObjectCoordinates.Instance;
            _game = GameManager.Instance;
            _ui = UIManager.Instance;
            _playerCamRotation = PlayerCamRotation.Instance;
            _borderFlag = BorderFlag.None;
            _pop = pop;
            _helperTextCalled = false;
            _lastDegree = _playerCamRotation.EulerAngles.x;
        }

        // Update is called once per frame
        private void FixedUpdate()
        {
            SetBorders();
        }

        private void SetBorders()
        {
            if (_game.State != GameState.BorderCalculation)
                return;
            switch (_borderFlag)
            {
                case BorderFlag.None:
                    SetLeftBorder();
                    return;
                case BorderFlag.Left:
                    SetRightBorder();
                    return;
                case BorderFlag.Right:
                    SetUpperBorder();
                    return;
                case BorderFlag.Up:
                    SetLowerBorder();
                    return;
                case BorderFlag.Down:
                case BorderFlag.All:
                default:
                    _game.State = GameState.InGame;
                    _ui.borderHelper.ClearBorderText();
                    break;
            }
        }

        private void SetLeftBorder()
        {
            if (!_helperTextCalled)
            {
                _ui.borderHelper.SetBorderTextLeft();
                _helperTextCalled = true;
            }
            float yRotation = _playerCamRotation.EulerAngles.y;
            if (yRotation is > 360 or < 180) return;
            if (Math.Abs(_lastDegree - yRotation) < 2.0)
                _pop--;
            else
            {
                _pop = pop;
                _lastDegree = yRotation;
                return;
            }

            if (_pop != 0) return;
            LeftDegree = yRotation;
            _borderFlag = BorderFlag.Left;
            _pop = pop;
            _helperTextCalled = false;
            _ui.debug.SpawnDebugObject(LeftBorder);
        }

        private void SetRightBorder()
        {
            if (!_helperTextCalled)
            {
                _ui.borderHelper.SetBorderTextRight();
                _helperTextCalled = true;
            }
            float yRotation = _playerCamRotation.EulerAngles.y;
            if (yRotation is > 180 or < 0) return;
            if (Math.Abs(_lastDegree - yRotation) < 2.0) 
                _pop--;
            else
            {
                _pop = pop;
                _lastDegree = yRotation;
            }

            if (_pop != 0) return;
            RightDegree = yRotation;
            _borderFlag = BorderFlag.Right;
            _pop = pop;
            _helperTextCalled = false;
            _ui.debug.SpawnDebugObject(RightBorder);
        }
        
        private void SetUpperBorder()
        {
            if (!_helperTextCalled)
            {
                _ui.borderHelper.SetBorderTextUp();
                _helperTextCalled = true;
            }
            float xRotation = _playerCamRotation.EulerAngles.x;
            if (xRotation is > 360 or < 180) return;
            if (Math.Abs(_lastDegree - xRotation) < 2.0) 
                _pop--;
            else
            {
                _pop = pop;
                _lastDegree = xRotation;
            }

            if (_pop != 0) return;
            UpperDegree = xRotation;
            _borderFlag = BorderFlag.Up;
            _pop = pop;
            _helperTextCalled = false;
            _ui.debug.SpawnDebugObject(UpperBorder);
        }

        private void SetLowerBorder()
        {
            if (!_helperTextCalled)
            {
                _ui.borderHelper.SetBorderTextDown();
                _helperTextCalled = true;
            }
            float xRotation = _playerCamRotation.EulerAngles.x;
            if (xRotation is > 180 or < 0) return;
            if (Math.Abs(_lastDegree - xRotation) < 2.0) 
                _pop--;
            else
            {
                _pop = pop;
                _lastDegree = xRotation;
            }

            if (_pop != 0) return;
            LowerDegree = xRotation;
            _borderFlag = BorderFlag.All;
            _pop = pop;
            _helperTextCalled = false;
            _game.State = GameState.InGame;
            _ui.borderHelper.ClearBorderText();
            _ui.debug.SpawnDebugObject(LowerBorder);
        }
        
        public string ConstructDebugString()
        {
            string res = "Pop: " + _pop + System.Environment.NewLine;
            if (_borderFlag >= BorderFlag.Left)
                res = res + "Left Border Set" + System.Environment.NewLine;
            if (_borderFlag >= BorderFlag.Right)
                res = res + "Right Border Set" + System.Environment.NewLine;
            if (_borderFlag >= BorderFlag.Up)
                res = res + "Upper Border Set" + System.Environment.NewLine;
            if (_borderFlag >= BorderFlag.Down)
                res = res + "Lower Border Set";
            return res;
        }

        public Vector3 LeftBorder { get; private set; }

        public Vector3 RightBorder { get; private set; }

        public Vector3 UpperBorder { get; private set; }

        public Vector3 LowerBorder { get; private set; }

        public float LeftDegree
        {
            get => _borderRotationDegrees[0];
            set
            {
                _borderRotationDegrees[0] = value;
                Vector3 tempBorder = _playerCamRotation.CameraPosition + _playerCamRotation.ForwardVector * _objectCoordinates.SpawnDistanceFromPlayer;
                tempBorder.y = 60;
                LeftBorder = tempBorder;
            }
        }
        
        public float RightDegree
        {
            get => _borderRotationDegrees[1];
            set
            {
                _borderRotationDegrees[1] = value;
                Vector3 tempBorder = _playerCamRotation.CameraPosition + _playerCamRotation.ForwardVector * _objectCoordinates.SpawnDistanceFromPlayer;
                tempBorder.y = 60;
                RightBorder = tempBorder;
            }
        }
        
        public float UpperDegree
        {
            get => _borderRotationDegrees[2];
            set
            {
                _borderRotationDegrees[2] = value;
                Vector3 tempBorder = _playerCamRotation.CameraPosition + _playerCamRotation.ForwardVector * _objectCoordinates.SpawnDistanceFromPlayer;
                tempBorder.x = 0;
                UpperBorder = tempBorder;
            }
        }
        
        public float LowerDegree
        {
            get => _borderRotationDegrees[3];
            set
            {
                _borderRotationDegrees[3] = value;
                Vector3 tempBorder = _playerCamRotation.CameraPosition + _playerCamRotation.ForwardVector * _objectCoordinates.SpawnDistanceFromPlayer;
                tempBorder.x = 0;
                LowerBorder = tempBorder;
            }
        }
    }
}