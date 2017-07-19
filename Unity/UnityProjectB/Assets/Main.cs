using UnityEngine;
using UnityEngine.UI;

public class Main : MonoBehaviour
{
    [SerializeField]
    private Text dataText;

    [SerializeField]
    private Text countButtonText;

    [SerializeField]
    private Button countButton;

    [SerializeField]
    private Button returnButton;

    private int count;

    void Awake()
    {
        countButton.onClick.AddListener(UGUI_HandleIncrementButtonClick);
        RefreshIncrementText();

        returnButton.onClick.AddListener(UGUI_HandleReturnButtonClick);
    }

    void RefreshIncrementText()
    {
        countButtonText.text = string.Format("Count={0}, add 1?", count);
    }

    /// <remarks>Called by native android.</remarks>
    void Android_SetData(string data)
    {
        dataText.text = string.Format("Android Data: '{0}'", data);
    }

    void UGUI_HandleIncrementButtonClick()
    {
        count++;
        RefreshIncrementText();
    }

    void UGUI_HandleReturnButtonClick()
    {
        AndroidUtilities.InvokeNativeMethod("unity_exit", count);
    }
}
