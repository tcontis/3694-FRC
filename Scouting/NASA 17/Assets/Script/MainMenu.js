/* --------------------------------------------
	Code written by Andrew Friedman
	Its legitness I know!
	4/6/2016
   --------------------------------------------*/
   
#pragma strict

function MainMenu() {
	//Loads team editor scene
	Application.LoadLevel("MainMenu");
}

//Button Scripts for MainMenu
function Info() {
	//Loads team editor scene
	Application.LoadLevel("Info");
}

function Calculator() {
	//Loads Calculator scene
	Application.LoadLevel("Calculator");
}

function Schedule() {
	//Goes to website
	Application.OpenURL ("http://coineaters.sexy/schedule.html");
}

function Exit() {
	//Quits App
	Application.Quit();
}

function Statistics() {
	//Goes to website
	Application.OpenURL ("http://coineaters.sexy/data.html");
}

function Cheat() {
	//Loads Cheat Sheet Scene
	Application.LoadLevel("Cheat");
}