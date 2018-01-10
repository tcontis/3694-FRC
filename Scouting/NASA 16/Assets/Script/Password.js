/* --------------------------------------------
	Code written by Andrew Friedman
	Its legitness I know!
	2/15/2016
   --------------------------------------------*/

#pragma strict
var pass = 3694;
var enter;
var inputField : UnityEngine.UI.InputField;

function Start () {

}


function Update () {

}

function Password () {
	enter = inputField.text;
	Debug.Log (enter);
	if (enter == "3694"){
		Debug.Log ("ITS WORKING");
		Application.LoadLevel("Teams");
		
	}
	else {
		Debug.Log ("ITS NOT WORKING");
	}
}

