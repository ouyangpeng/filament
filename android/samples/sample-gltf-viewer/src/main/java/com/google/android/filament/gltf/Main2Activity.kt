/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.filament.gltf

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import com.google.android.filament.utils.*

class Main2Activity : Activity() {
    companion object {
        // Load the library for the utility layer, which in turn loads gltfio and the Filament core.
        init {
            Utils.init()
        }

        private const val TAG = "gltf-viewer"
    }

    private lateinit var surfaceView: SurfaceView
    var customViewer: CustomViewer = CustomViewer()

    private lateinit var backButton: ImageButton


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.simple_layout2)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        backButton = findViewById(R.id.back)
        backButton.setOnClickListener {
            finish()
        }

        surfaceView = findViewById(R.id.main_sv)

        customViewer.run {
            loadEntity()
            setSurfaceView(surfaceView)

            //directory and model each as param
            loadGlb(this@Main2Activity, "grogu", "grogu")
            //loadGlb(this@Main2Activity, "tiger", "tiger")

            //loadGltf(this@Main2Activity, "warcraft", "scene")

            //directory and model as one
            // loadGlb(this@Main2Activity, "grogu/grogu")

            //Enviroments and Lightning (OPTIONAL)
            loadIndirectLight(this@Main2Activity, "venetian_crossroads_2k", null)
            loadEnviroment(this@Main2Activity, "venetian_crossroads_2k", null)
        }
    }

    override fun onResume() {
        super.onResume()
        customViewer.onResume()
    }

    override fun onPause() {
        super.onPause()
        customViewer.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        customViewer.onDestroy()
    }
}