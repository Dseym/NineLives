package ru.dseymo.nineLives;

import java.io.File;

import ru.dseymo.nineLives.utils.Config;

public class MainConfig extends Config {

	public MainConfig(File file) {
		super(file, true);
	}
	
	public boolean isBanning() {return getBoolean("banning");}
	public int getStartLives() {return getInt("startLives");}

}
