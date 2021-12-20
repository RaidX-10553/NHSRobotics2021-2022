// Autonomous Period
// Starting at Blue wall | Storage side 
// Scoring pre-loaded box
// Going to Carousel and delivering duck onto ground 
// Parking completely in warehouse

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

@Autonomous(name="AutoBlueStorage", group="Autonomous")
public class AutonomousBlueStorage extends LinearOpMode {

    /* Declare OpMode members. */

    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
    private static final String[] LABELS = {
      "Ball",
      "Cube",
      "Duck",
      "Marker"
    };

    private static final String VUFORIA_KEY =
            "AVgDgHH/////AAABmYSjDOxUjkoloTDlPbTfcxMdY+UnPMMGHvIoENz7ljjIJLU7u/WzCXUMDrkDD3rtaVaTTqHY2RiMeeBO0+nWwRe3aHkzxtpSa0LEdicMGhjyk0JyTKusUjg3l0kj1xYOmTidIjIlCc18/Z3FKZTBKEwZrSgakYxiot2r4zBdXcyMekArDle5NCxpDHATu261ZnwhBJc7UKazEkRCbtn9qaN0a5dB0kX3dhGxrargryTg0AuEj17NaXxy8tnq10HEXb2NiwvOJVFiw3YJhEMvyUq5bmY/c0yEchStOyy2bOswp5xtXE5+Qwy8Ty474gYH5ROWRdwrf+6mzFtS4CGdotST1dAOo3uuMgcTNvxsU4CZ";


    private VuforiaLocalizer vuforia;

    private TFObjectDetector tfod;

    @Override
    public void runOpMode() {

        initVuforia();
        initTfod();

        if (tfod != null) {
            tfod.activate();
            tfod.setZoom(2.5, 16.0/9.0);
        }

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start Autonomous");
        telemetry.update();
        

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        
        if (opModeIsActive()) {
            while (opModeIsActive()) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                      telemetry.addData("# Object Detected", updatedRecognitions.size());

                      // step through the list of recognitions and display boundary info.
                      int i = 0;
                      boolean isDuckDetected = false;  
                      for (Recognition recognition : updatedRecognitions) {
                        telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                        telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                          recognition.getLeft(), recognition.getTop());
                        telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                recognition.getRight(), recognition.getBottom());
                        i++;
                        
                         // check label to see if the camera now sees a Ball         ** ADDED **
                        if (recognition.getLabel().equals("Duck")) {            //  ** ADDED **
                             isDuckDetected = true;                             //  ** ADDED **
                             telemetry.addData("Object Detected", "Duck");      //  ** ADDED **
                         } else {                                               //  ** ADDED **
                             isDuckDetected = false;   
                             telemetry.addData("Object Not Detected", "Duck");   //  ** ADDED **
                         }                                                      //  ** ADDED **
                      }
                      telemetry.update();
                    }
                }
            }
        }
        

        // Continue Auto here with roadrunner


    }
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
            "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.5f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }
}
