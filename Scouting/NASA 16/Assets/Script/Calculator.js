/* --------------------------------------------
	Code written by Andrew Friedman
	Its legitness I know!
	2/18/2016
   --------------------------------------------*/

#pragma strict
//creates da score var
var Score : int;
//Grabs inputfield and textbox
var ScoreT : UnityEngine.UI.Text;

function Start () {

}

function Update () {

}


//Function for button
function RDefense () {
	Score += 2;
	PlayerPrefs.SetString("ScoreT", "Number: " + Score);
	ScoreT.text = PlayerPrefs.GetString("ScoreT");
}
//Function for button
function CDefense () {
	Score += 10;
	PlayerPrefs.SetString("ScoreT", "Number: " + Score);
	ScoreT.text = PlayerPrefs.GetString("ScoreT");
}
//Function for button
function LowGoal () {
	Score += 5;
	PlayerPrefs.SetString("ScoreT", "Number: " + Score);
	ScoreT.text = PlayerPrefs.GetString("ScoreT");
}
//Function for button
function HighGoal () {
	Score += 10;
	PlayerPrefs.SetString("ScoreT", "Number: " + Score);
	ScoreT.text = PlayerPrefs.GetString("ScoreT");
}
//Function for button
function Foul () {
	Score += 5;
	PlayerPrefs.SetString("ScoreT", "Number: " + Score);
	ScoreT.text = PlayerPrefs.GetString("ScoreT");
}
//Function for button
function Clear () {
	Score = 0;
	PlayerPrefs.SetString("ScoreT", "Number: " + Score);
	ScoreT.text = PlayerPrefs.GetString("ScoreT");
}
