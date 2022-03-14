package com.example.miko

import android.Manifest
import android.Manifest.permission.CAMERA
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.miko.databinding.FragmentCameraBinding
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CameraFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CameraFragment : Fragment() {

    private lateinit var binding: FragmentCameraBinding
    private var saveImageToGallery: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentCameraBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity?)!!.supportActionBar.apply {
            this!!.title ="Camera Fragment"
        }
        binding.btnCamera.setOnClickListener {
            takePhotoFromCamera()
        }
        return binding.root
    }

    private fun takePhotoFromCamera(){
        Dexter.withContext(activity)
            .withPermissions(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA)
            .withListener(object: MultiplePermissionsListener {
                override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                    if (p0!!.areAllPermissionsGranted()){

                        val galleryIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                        startActivityForResult(galleryIntent, CAMERA)

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


    internal fun showRationalDialogForPermission(){
        AlertDialog.Builder(activity)
            .setMessage("Storage Permission Is Required For Accessing Photo From Gallery! You can enable it from App Settings")
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
    


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){
    if (requestCode == CAMERA){
                val thumbnail: Bitmap = data!!.extras!!.get("data") as Bitmap
                binding.ivImageView.setImageBitmap(thumbnail)
                //saveImageToGallery = saveImageToInternalStorage(thumbnail)
                Log.i("Saved Image:", saveImageToGallery.toString())

            }
        }
    }

    companion object{
        private const val CAMERA = 2

    }


}