### General
This code provide utils for connecting PetriSim objects in [PetriObjModel library](https://github.com/StetsenkoInna/PetriObjModelPaint "PetriObjModel library")

### Usage

1. Clone repository `git clone git@github.com:MihailYa/petriUtils.git`
2. Copy `petriUtils` package
3. Paste package in the `PetriObjModelPaint\src` folder of the [PetriObjModel library](https://github.com/StetsenkoInna/PetriObjModelPaint "PetriObjModel library")

### Features
Merge Petri positions using PetriNetConnector:

    ArrayList<PetriSim> petriSims =
    	new PetriNetConnector( )
    		.addPetriSim("firstPetriSim", petriSim1)
    		.addPetriSim("secondPetriSim", petriSim2)
    		.addPetriSim("thirdPetriSim", petriSim3)
    
    		.startMerge("firstPetriStm", "secondPetriSim" )
    		.merge("posNamel", "“posName2" )
			.mergeSameName("posJointName") // same as .merge("jointName", "“posJointName" )
    		
			.startMerge("thirdPetriStm", "secondPetriSim" )
			.merge("posName3", "“posName4" )
	
    		.buildList(); // or .buildModel() for PetriObjModel building

Merge without names:

    new MultipleNetConnector()
    	.mergeSameName("jointPosName1")
    	.mergeSameName("jointPosName2")
    	
    	.buildList(); // or .buildModel() for PetriObjModel building

Create helper for get/set Petri position:

    PetriSimUtilHelper helper = createHelperForPetriSim(yourPetriSim);
    PetriP pos1 = helper.getP("posName1");
    anotherHelper.setP("posName2", pos1);

Another static helper methods:

    // Create helpers
	PetriSimUtilHelper createHelperForPetriSim(PetriSim petriSim);
    PetriSimUtilHelper createHelperForList(PetriP[] petriPList);
    
    Pair<Integer, PetriP> getPetriPWithIndex(PetriP[] petriPlist, String name);
    
	// Get/Set using PetriP list 
    PetriP getPetriP(PetriP[] petriPlist, String name);
    void setPetriP(PetriP[] petriPlist, String name, PetriP newPetriP);
    
	// Get/Set using PetriSim
    PetriP getPetriP(PetriSim petriSim, String name);
    void setPetriP(PetriSim petriSim, String name, PetriP newPetriP);