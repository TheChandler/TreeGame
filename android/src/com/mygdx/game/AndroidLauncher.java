package com.mygdx.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.mygdx.game.TreeGame;
import android.view.*;

public class AndroidLauncher extends AndroidApplication {
	@Override

	// This snippet hides the system bars.

	protected void onCreate (Bundle savedInstanceState) {


		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new TreeGame(), config);
	}

}
