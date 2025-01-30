 using System;
 using Managers;
 using UnityEngine;

namespace Controllers
{
    public class CamControlPC : MonoBehaviour
    {
        public float senX;
        public float senY;

        private float _xRotation;
        private float _yRotation;
        
        private void Start()
        {
            Cursor.lockState = CursorLockMode.Locked;
            Cursor.visible = false;
        }
        
        private void Update()
        {
            RotateCam();
        }

        private void RotateCam()
        {
            float mouseX = Input.GetAxisRaw("Mouse X") * Time.deltaTime * senX;
            float mouseY = Input.GetAxisRaw("Mouse Y") * Time.deltaTime * senY;

            _yRotation += mouseX;
            _xRotation -= mouseY;
        
            transform.rotation = Quaternion.Euler(_xRotation, _yRotation, 0);
        }


        public void SetSensitivity(float x, float y)
        {
            senX = x;
            senY = y;
        }
    }
}
