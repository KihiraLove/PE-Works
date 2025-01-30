using UnityEngine.UI;

namespace Managers.SubManagers
{
    public class BorderHelper
    {
        private readonly Text _borderHelper;

        public string HelperText
        {
            get => _borderHelper.text;
            set => _borderHelper.text = value;
        }

        public BorderHelper(Text borderHelper)
        {
            _borderHelper = borderHelper;
        }
        
        public void SetBorderTextLeft()
        {
            HelperText = "Please turn your head left," + System.Environment.NewLine + "as far as you can!";
        }
        
        public void SetBorderTextRight()
        {
            HelperText = "Please turn your head right," + System.Environment.NewLine + "as far as you can!";
        }
        
        public void SetBorderTextUp()
        {
            HelperText = "Please turn your head up," + System.Environment.NewLine + "as far as you can!";
        }
        
        public void SetBorderTextDown()
        {
            HelperText = "Please turn your head down," + System.Environment.NewLine + "as far as you can!";
        }

        public void ClearBorderText()
        {
            HelperText = "";
        }
    }
}