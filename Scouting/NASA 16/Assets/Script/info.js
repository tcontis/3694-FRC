/* --------------------------------------------
	Code written by Andrew Friedman
	Its legitness I know!
	4/6/2016
   --------------------------------------------*/

#pragma strict
import System.IO;
import System;  // Used for getting the date
//All the toggles and values
var Toggle1 : UnityEngine.UI.Toggle;
var toggler1 : boolean;
var toggler2 : boolean;
var toggler3 : boolean;
var toggler4 : boolean;
var toggler5 : boolean;
var toggler6 : boolean;
var toggler7 : boolean;
var toggler8 : boolean;
var toggler9 : boolean;
var toggler10 : boolean;
var toggler11 : boolean;
var toggler12 : boolean;
var toggler13 : boolean;
var toggler14 : boolean;
var toggler15 : boolean;
//Grabs both inputfields
var TinputField : UnityEngine.UI.InputField;
var NinputField : UnityEngine.UI.InputField;
var Round : UnityEngine.UI.InputField;
//All the functions for the toggles 
var PSA : GameObject; //Cheating object
var dingus = 1;


function DATOGGLER1() {
	PlayerPrefs.SetInt("toggler1" + Application.loadedLevelName, (toggler1 ? 1 : 0));
	toggler1 = (PlayerPrefs.GetInt("toggler1" + Application.loadedLevelName) !=1);
}
function DATOGGLER2() {
	PlayerPrefs.SetInt("toggler2" + Application.loadedLevelName, (toggler2 ? 1 : 0));
	toggler2 = (PlayerPrefs.GetInt("toggler2" + Application.loadedLevelName) !=1);
}
function DATOGGLER3() {
	PlayerPrefs.SetInt("toggler3" + Application.loadedLevelName, (toggler3 ? 1 : 0));
	toggler3 = (PlayerPrefs.GetInt("toggler3" + Application.loadedLevelName) !=1);
}
function DATOGGLER4() {
	PlayerPrefs.SetInt("toggler4" + Application.loadedLevelName, (toggler4 ? 1 : 0));
	toggler4 = (PlayerPrefs.GetInt("toggler4" + Application.loadedLevelName) !=1);
}
function DATOGGLER5() {
	PlayerPrefs.SetInt("toggler5" + Application.loadedLevelName, (toggler5 ? 1 : 0));
	toggler5 = (PlayerPrefs.GetInt("toggler5" + Application.loadedLevelName) !=1);
}
function DATOGGLER6() {
	PlayerPrefs.SetInt("toggler6" + Application.loadedLevelName, (toggler6 ? 1 : 0));
	toggler6 = (PlayerPrefs.GetInt("toggler6" + Application.loadedLevelName) !=1);
}
function DATOGGLER7() {
	PlayerPrefs.SetInt("toggler7" + Application.loadedLevelName, (toggler7 ? 1 : 0));
	toggler7 = (PlayerPrefs.GetInt("toggler7" + Application.loadedLevelName) !=1);
}
function DATOGGLER8() {
	PlayerPrefs.SetInt("toggler8" + Application.loadedLevelName, (toggler8 ? 1 : 0));
	toggler8 = (PlayerPrefs.GetInt("toggler8" + Application.loadedLevelName) !=1);
}
function DATOGGLER9() {
	PlayerPrefs.SetInt("toggler9" + Application.loadedLevelName, (toggler9 ? 1 : 0));
	toggler9 = (PlayerPrefs.GetInt("toggler9" + Application.loadedLevelName) !=1);
}
function DATOGGLER10() {
	PlayerPrefs.SetInt("toggler10" + Application.loadedLevelName, (toggler10 ? 1 : 0));
	toggler10 = (PlayerPrefs.GetInt("toggler10" + Application.loadedLevelName) !=1);
}
function DATOGGLER11() {
	PlayerPrefs.SetInt("toggler11" + Application.loadedLevelName, (toggler11 ? 1 : 0));
	toggler11 = (PlayerPrefs.GetInt("toggler11" + Application.loadedLevelName) !=1);
}
function DATOGGLER12() {
	PlayerPrefs.SetInt("toggler12" + Application.loadedLevelName, (toggler12 ? 1 : 0));
	toggler12 = (PlayerPrefs.GetInt("toggler12" + Application.loadedLevelName) !=1);
}
function DATOGGLER13() {
	PlayerPrefs.SetInt("toggler13" + Application.loadedLevelName, (toggler13 ? 1 : 0));
	toggler13 = (PlayerPrefs.GetInt("toggler13" + Application.loadedLevelName) !=1);
}
function DATOGGLER14() {
	PlayerPrefs.SetInt("toggler14" + Application.loadedLevelName, (toggler14 ? 1 : 0));
	toggler14 = (PlayerPrefs.GetInt("toggler14" + Application.loadedLevelName) !=1);
}
function DATOGGLER15() {
	PlayerPrefs.SetInt("toggler15" + Application.loadedLevelName, (toggler15 ? 1 : 0));
	toggler15 = (PlayerPrefs.GetInt("toggler15" + Application.loadedLevelName) !=1);
}

function Cheater() {
	if (dingus == 1) {
		PSA.SetActive(true);	
		dingus = 0;
	} 
	else if (dingus == 0) {
		PSA.SetActive(false);
		dingus = 1;
	}
}



//All the shit that saves
function MainMenu() {
	if (TinputField.text == "") {
		Application.LoadLevel("MainMenu");
	}
	else {
		var sw = new System.IO.StreamWriter("Info.txt", true); //declares var
		sw.WriteLine("Team #" + TinputField.text);
		sw.WriteLine("Round #" + Round.text);
		sw.Write("The date is: ");
		sw.WriteLine(DateTime.Now);
		sw.WriteLine("High Goal = "  + toggler1);
		sw.WriteLine("Low Goal = " + toggler2);
		sw.WriteLine("Porticullis = " + toggler3);
		sw.WriteLine("Cheval De Fris = " + toggler4);
		sw.WriteLine("Ramparts = " + toggler5);
		sw.WriteLine("Moat = " + toggler6);
		sw.WriteLine("Rock Wall = " + toggler7);
		sw.WriteLine("Drawbridge = " + toggler8);
		sw.WriteLine("Sallyport = " + toggler9);
		sw.WriteLine("Rough Terrain = " + toggler10);
		sw.WriteLine("Survived Round = " + toggler11);
		sw.WriteLine("Low Bar = " + toggler12);
		sw.WriteLine("Climb Tower = " + toggler13);
		sw.WriteLine("End near Tower = " + toggler14);
		sw.WriteLine("Dingus = " + toggler15);
		sw.WriteLine("Notes: " + NinputField.text);
		sw.WriteLine("----------");
		sw.Flush();
    	sw.Close();
	
	//Loads team editor scene
	Application.LoadLevel("MainMenu");
	}
}