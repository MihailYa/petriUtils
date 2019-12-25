package petriUtils.petriConnectorUtil;

import PetriObj.PetriObjModel;
import PetriObj.PetriP;
import PetriObj.PetriSim;
import petriUtils.PetriSimUtil;

import java.util.ArrayList;

public class MultipleNetConnector {
	public static MultipleNetConnector mergeMultiplePetriSims(ArrayList<PetriSim> petriSims, String jointPName) {
		return new MultipleNetConnector(petriSims)
				.mergeSameName(jointPName);
	}

	private ArrayList<PetriSim> petriSims;
	private ArrayList<PetriSimUtil.PetriSimUtilHelper> petriSimHelpers = new ArrayList<>();

	public MultipleNetConnector(ArrayList<PetriSim> petriSims) {
		this.petriSims = petriSims;
		for (PetriSim petriSim : petriSims) {
			petriSimHelpers.add(PetriSimUtil.createHelperForList(petriSim.getNet()
			                                                             .getListP()));
		}
	}

	public MultipleNetConnector() {
	}

	public MultipleNetConnector addPetriSim(PetriSim petriSim) {
		petriSimHelpers.add(PetriSimUtil.createHelperForList(petriSim.getNet()
		                                                             .getListP()));

		return this;
	}

	public MultipleNetConnector mergeSameName(String jointPName) {
		if (petriSimHelpers.size() <= 1) {
			throw new RuntimeException("Wrong number of merged petriSims (should be at least one)");
		}

		PetriP mergePetriP = petriSimHelpers.get(0)
		                                    .getP(jointPName);

		for (PetriSimUtil.PetriSimUtilHelper petriSimHelper : petriSimHelpers) {
			petriSimHelper.setP(jointPName, mergePetriP);
		}

		return this;
	}

	public ArrayList<PetriSim> buildList() {
		return petriSims;
	}

	public PetriObjModel buildModel() {
		return new PetriObjModel(petriSims);
	}
}
