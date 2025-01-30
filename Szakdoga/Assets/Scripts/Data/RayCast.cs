using Controllers;
using Enums;
using Managers;
using UnityEngine;

namespace Data
{
    public class RayCast : MonoBehaviour
    {
        public Camera cam;

        private UIManager _ui;
        private GameManager _game;
        private PlayerCamRotation _playerCamera;

        private void Start()
        {
            _ui = UIManager.Instance;
            _game = GameManager.Instance;
            _playerCamera = PlayerCamRotation.Instance;
        }

        private void Update()
        {
            CheckForRayHit();
        }

        private void CheckForRayHit()
        {
            Ray ray = cam.ViewportPointToRay(new Vector3(0.5f, 0.5f, 0f));
            Vector3 rayDirection = ray.direction * 100f;
            if (_ui.debug.Enabled)
            {
                Debug.DrawRay(ray.origin, rayDirection, Color.red);
            }
            if (!MainController.Instance.IsMainInput()) return;
            if (!Physics.Raycast(ray.origin, rayDirection, out RaycastHit hit)) return;
            GameObject o = hit.collider.gameObject;
            _ui.debug.RaycastDebugText = "Ray collided with " + o.name;
            switch (o.name)
            {
                case "DebugClickBox":
                    _game.SwitchDebugMode();
                    return;
                case "StartClickBox":
                    _game.State = GameState.ExerciseMenu;
                    return;
                case "EditClickBox":
                    _game.State = GameState.EditMode;
                    return;
                case "ExitClickBox":
                    Application.Quit();
                    return;
            }
        }
    }
}