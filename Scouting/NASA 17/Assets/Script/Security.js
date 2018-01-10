/* --------------------------------------------
	Code written by Andrew Friedman
	Its legitness I know!
	3/23/2017 (Day before Competition)
   --------------------------------------------*/
   
#pragma strict
var Pass : UnityEngine.UI.InputField;
var Title : UnityEngine.UI.Text;

function Start () {
	if (PlayerPrefs.GetInt("Leet") == 1) { //Check if this is the first time
		Application.LoadLevel("MainMenu");
	}else {
		Debug.Log("User has not logged in before");
	}
}

function Update () {
	
}

function Next () {
	if (Pass.text == "andrewisbetter") {
		PlayerPrefs.SetInt("Leet", 1);
		Application.LoadLevel("MainMenu");
	}else {
		PlayerPrefs.SetInt("Counter", PlayerPrefs.GetInt("Counter") + 1);
		Title.text = ("Wrong *" + PlayerPrefs.GetInt("Counter"));
		Debug.Log("Wrong");
	}
}

function ESCAPE() {
	Application.Quit(); //Quits App (Duh!)
}