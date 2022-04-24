package artisynth.istar.hamidreza2.models.dynjaw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import artisynth.core.inverse.InverseManager;
import artisynth.core.inverse.TrackingController;
import artisynth.core.inverse.InverseManager.ProbeID;
import artisynth.core.mechmodels.Muscle;

public class JawHamidreza extends JawLarynxDemo{

	HashMap<String,String> InsversMuscles = new HashMap<String,String>();
   
   public JawHamidreza() {
      super();
   }
   
   
   
   @Override
   public void build (String[] args) throws IOException {
       
	   super.build (args);
	    
	   
	    InsversMuscles.put("lip","Left Inferior Lateral Pterygoid");
		InsversMuscles.put("rip","Right Inferior Lateral Pterygoid");
		InsversMuscles.put("lsp","Lateral Pterygoid");
		InsversMuscles.put("rsp","Right Superior Lateral Pterygoid");
		InsversMuscles.put("lad","Left Anterior Digastric");
		InsversMuscles.put("rad","Right Anterior Digastric");
		InsversMuscles.put("lam","Left Mylohyoid");
		InsversMuscles.put("ram","Right Mylohyoid");
		InsversMuscles.put("lgh","Left Geniohyoid");
		InsversMuscles.put("rgh","Right Geniohyoid");
		InsversMuscles.put("lpd","Left Posterior Digastric");
		InsversMuscles.put("rpd","Right Posterior Digastric");
		InsversMuscles.put("lsteh","Left Sternohyoid");
		InsversMuscles.put("rsteh","Right Sternohyoid");
		InsversMuscles.put("lat","Left Anterior Temporal");
		InsversMuscles.put("rat","Right Anterior Temporal");
		InsversMuscles.put("lmt","Left Middle Temporal");
		InsversMuscles.put("rmt","Right Middle Temporal");
		InsversMuscles.put("lpt","Left Posterior Temporal");
		InsversMuscles.put("rpt","Right Posterior Temporal");
		InsversMuscles.put("lsm","Left Superficial Masseter");
		InsversMuscles.put("rsm","Right Superficial Masseter");
		InsversMuscles.put("lmp","Left Medial Pterygoid");
		InsversMuscles.put("rmp","Right Medial Pterygoid");
		InsversMuscles.put("ldm","Left Deep Masseter");
		InsversMuscles.put("rdm","Right Deep Masseter");


       
       inverseSetup();

           
   }
   

   public void inverseSetup() {
		TrackingController myTrackingController = new TrackingController(myJawModel, "incisor_disp");
		 
		for (Muscle muscle : myJawModel.myMuscles) {
		    for (String invMuscle: InsversMuscles.keySet()) {
		    	if (invMuscle.equals(muscle.getName())){
			    	myTrackingController.addExciter(muscle);	
		    	}	
		    	
		    }
			
		}
		
		myTrackingController.addMotionTarget(myJawModel.frameMarkers().get("lowerincisor"));
		myTrackingController.addL2RegularizationTerm(0.1);
		myTrackingController.createProbesAndPanel (this);
	     
		InverseManager.setInputProbeData (
	    		this,
	            ProbeID.TARGET_POSITIONS,
	            new double[] { 0.0, -47.9584, 41.7642}, 
	            /*timestep=*/0.1);

	     addController(myTrackingController);
	     
	}
   
   
   
}

