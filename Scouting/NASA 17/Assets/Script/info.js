/* --------------------------------------------
	Code written by Andrew Friedman
	Its legitness I know!
	4/6/2016
   --------------------------------------------*/

#pragma strict
import System.IO;
import System;  // Used for getting the date

//GENERAL SHIT
var GeneralBox : GameObject;
var Team : UnityEngine.UI.InputField;
var Round : UnityEngine.UI.InputField;
var Colors : UnityEngine.UI.InputField;
var Vision : UnityEngine.UI.InputField;
var GNotes : UnityEngine.UI.InputField;
//AUTO SHIT
var AutoBox : GameObject;
var White : UnityEngine.UI.Toggle;
var GearA : UnityEngine.UI.Toggle;
var ATop : UnityEngine.UI.InputField;
var ALow : UnityEngine.UI.InputField;
var ANotes : UnityEngine.UI.InputField;
//TELE SHIT
var TeleBox : GameObject;
var GearT : UnityEngine.UI.InputField;
var TTop : UnityEngine.UI.InputField;
var TLow : UnityEngine.UI.InputField;
var Rope : UnityEngine.UI.Toggle;
var Lights : UnityEngine.UI.Toggle;
var TNotes : UnityEngine.UI.InputField;
//OTHER SHIT
var Error : UnityEngine.UI.Text;
var ExitCheck : GameObject;
var SubmitCheck : GameObject;

function Start () {
	Debug.Log(Rope.isOn);
}

//TOP BUTTONS
function GENBUTTON () {
	GeneralBox.SetActive (true);
	AutoBox.SetActive (false);
	TeleBox.SetActive (false);
}

function AUTOBUTTON () {
	GeneralBox.SetActive (false);
	AutoBox.SetActive (true);
	TeleBox.SetActive (false);
}

function TELEBUTTON () {
	GeneralBox.SetActive (false);
	AutoBox.SetActive (false);
	TeleBox.SetActive (true);
}

//BOTTOM BUTTONS
function Exit () {
	Application.LoadLevel("MainMenu");
	//Below Code doesnt work idk to late to fix
	//SubmitCheck.SetActive (true);
}

function ExitNo () {
	SubmitCheck.SetActive (false);
}

function ExitYes () {
	Application.LoadLevel("MainMenu");
}

function Submit () {
	if (Team.text == "" || Round.text == "") {
		if (Team.text == "") {
			Error.text = "Put in Team #!!!";
		}else if (Round.text == "") {
			Error.text = "Put in Round #!!!";
		}
	}else {
		SubmitCheck.SetActive (true);
	}
}

function SubmitNo () {
	SubmitCheck.SetActive (false);
}

function SubmitYes () {
	SAVE();
}

function SAVE () { //All the shit that saves
	Math();
	var sw = new System.IO.StreamWriter("Info.txt", true); //declares var
	sw.WriteLine(Team.text + "^" + DateTime.Now + "^" + Round.text + "^" + Colors.text + "^" + Vision.text + "^" + GNotes.text + "^" + White.isOn + "^" + GearA.isOn + "^" + ATop.text + "^" + ALow.text + "^" + ANotes.text + "^" + GearT.text + "^" + TTop.text + "^" + TLow.text + "^" + Rope.isOn + "^" + Lights.isOn + "^" + TNotes.text + "^" + PlayerPrefs.GetInt("APoints") + "^" + PlayerPrefs.GetInt("TPoints") + "^" + PlayerPrefs.GetInt("TOTALPOINTS"));
	sw.Flush();
    sw.Close();
	//Loads team editor scene
	Application.LoadLevel("MainMenu");
}

function Math () {
	PlayerPrefs.SetInt("APoints", 0);
	PlayerPrefs.SetInt("TPoints", 0);
	
	//AUTO POINTS
	if (White.isOn == true) {
		PlayerPrefs.SetInt("APoints", PlayerPrefs.GetInt("APoints") + 5);
	}
	PlayerPrefs.SetInt("APoints", PlayerPrefs.GetInt("APoints") + (int.Parse(ALow.text)/3));
	PlayerPrefs.SetInt("APoints", PlayerPrefs.GetInt("APoints") + int.Parse(ATop.text));
	if (GearA.isOn == true) {
		PlayerPrefs.SetInt("APoints", PlayerPrefs.GetInt("APoints") + 60);
	}
	//TELE POINTS
	PlayerPrefs.SetInt("TPoints", PlayerPrefs.GetInt("TPoints") + (int.Parse(TLow.text)/9));
	PlayerPrefs.SetInt("TPoints", PlayerPrefs.GetInt("TPoints") + (int.Parse(TTop.text)/3));
	PlayerPrefs.SetInt("TPoints", PlayerPrefs.GetInt("TPoints") + (int.Parse(GearT.text)*20));
	//TOTAL
	PlayerPrefs.SetInt("TOTALPOINTS", PlayerPrefs.GetInt("APoints") + PlayerPrefs.GetInt("TPoints"));
}