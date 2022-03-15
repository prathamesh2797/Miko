package com.example.miko

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.miko.databinding.FragmentMicBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import androidx.appcompat.app.AppCompatActivity




/**
 * A simple [Fragment] subclass.
 * Use the [MicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MicFragment : Fragment() {

    private lateinit var binding: FragmentMicBinding
    private lateinit var mediaRecorder: MediaRecorder
    var path: String = Environment.getExternalStorageDirectory().toString() + "/myrec.3gp"

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=  FragmentMicBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity?)!!.supportActionBar!!.title="Mic Fragment"

        binding.btnPlay.isEnabled= false

        binding.btnStart.setOnClickListener {
            RecordAudio()
            binding.btnPlay.isEnabled= false


        }

        binding.btnPlay.setOnClickListener {
            var mP = MediaPlayer()
            mP.setDataSource(path)
            mP.prepare()
            mP.start()

        }

        return binding.root
    }

    internal fun showRationalDialogForPermission(){
        AlertDialog.Builder(activity)
            .setMessage("Recordinf Permission Is Required For Accessing This Feature You can enable it from App Settings")
            .setPositiveButton("Settings"){
                    _, _ ->

                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", "com.example.miko",null)
                    intent.data = uri
                    startActivity(intent)
                }catch (e:Exception){
                    Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel"){
                    dialog, which ->
                dialog.cancel()
                dialog.dismiss()
            }
            .show()


    }

    private fun  RecordAudio(){
        Dexter.withContext(activity)
            .withPermissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO)
            .withListener(object: MultiplePermissionsListener {
                @SuppressLint("WrongConstant")
                override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                    if (p0!!.areAllPermissionsGranted()){

                        mediaRecorder = MediaRecorder()
                        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC)
                        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB)
                        mediaRecorder.setOutputFile(path)
                        mediaRecorder.prepare()
                        mediaRecorder.start()


                        Handler(Looper.getMainLooper()).postDelayed({
                            mediaRecorder.stop()
                            binding.btnPlay.isEnabled= true

                        },10000)


                        Toast.makeText(activity, "Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                    else if (p0.isAnyPermissionPermanentlyDenied){
                        showRationalDialogForPermission()
                    }else{
                        Toast.makeText(activity, "Storage Permission Is Required To Access Gallery", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {
                    p1?.continuePermissionRequest()

                }

            }).onSameThread().check()

    }
}