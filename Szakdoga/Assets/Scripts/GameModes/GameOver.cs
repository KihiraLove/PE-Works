using Enums;
using Managers;
using UnityEngine;

namespace GameModes
{
    public class GameOver : MonoBehaviour
    {
        private GameManager _game;
        private UIManager _ui;

        private void Start()
        {
            _game = GameManager.Instance;
            _ui = UIManager.Instance;
        }

        private void Update()
        {
            if (_game.State != GameState.GameOver) return;
            _game.State = GameState.Menu;
        }
    }
}