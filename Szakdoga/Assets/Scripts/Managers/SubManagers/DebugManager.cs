using Data;
using UnityEngine;
using UnityEngine.UI;

namespace Managers.SubManagers
{
    public class DebugManager
    {
        private readonly Text _debugText;
        private readonly Text _debugText2;
        private readonly Text _raycastDebugText;
        private int _buffer;
        private bool _enableDebug;
        private GameObject _debugSpherePrefab;
        private GameObject[] _debugObjects;

        public bool Enabled => _enableDebug;

        private readonly Border _border;

        public DebugManager(Text debugText, Text debugText2, Text raycastDebugText, GameObject debugSpherePrefab)
        {
            _debugText = debugText;
            _debugText2 = debugText2;
            _raycastDebugText = raycastDebugText;
            _debugSpherePrefab = debugSpherePrefab;
            _buffer = 60;
            _enableDebug = false;
            _debugText.enabled = false;
            _debugText2.enabled = false;
            _raycastDebugText.enabled = false;
            _border = Border.Instance;
        }

        public string DebugText
        {
            get => _enableDebug ? _debugText.text : "";
            set => _debugText.text = value;
        }

        public string DebugText2
        {
            get => _enableDebug ? _debugText2.text : "";
            set => _debugText2.text = value;
        }

        public string RaycastDebugText
        {
            get => _enableDebug ? _raycastDebugText.text : "";
            set => _raycastDebugText.text = value;
        }

        public void SwitchDebugMode()
        {
            _debugText.enabled = !_debugText.enabled;
            _debugText2.enabled = !_debugText2.enabled;
            _raycastDebugText.enabled = !_raycastDebugText.enabled;
            _enableDebug = !_enableDebug;
        }

        public void LogValues()
        {
            if (_buffer-- != 0) return;
            Debug.Log(_debugText.text);
            Debug.Log(_debugText2.text);
            Debug.Log(_raycastDebugText.text);
            _buffer = 60;
        }

        public void CamRotationValues(float y, float x)
        {
            DebugText = "Y rotation: " + y + System.Environment.NewLine
                         + "X rotation: " + x + System.Environment.NewLine
                         + _border.ConstructDebugString();
        }

        public void SpawnDebugObject(Vector3 position)
        {
            GameManager.Instance.SpawnObject(_debugSpherePrefab, position);
        }
    }
}