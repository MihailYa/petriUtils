package petriUtils.petriConnectorUtil;

import PetriObj.PetriObjModel;
import PetriObj.PetriP;
import PetriObj.PetriSim;
import petriUtils.PetriSimUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class PetriNetConnector {
	private HashMap<String, PetriSimUtil.PetriSimUtilHelper> petriSimUtilHelpers = new HashMap<>();
	private ArrayList<PetriSim> petriSims = new ArrayList<>();

	private PetriSimUtil.PetriSimUtilHelper[] mergingPetriSims;

	public PetriNetConnector addPetriSim(String name, PetriSim petriSim) {
		petriSimUtilHelpers.put(name, PetriSimUtil.createHelperForList(petriSim.getNet()
		                                                                       .getListP()));
		petriSims.add(petriSim);

		return this;
	}

	public PetriNetConnector startMerge(String... petriSimNames) {
		if (petriSimNames.length <= 1) {
			throw new RuntimeException("PetriSims merging count should be at least two");
		}

		mergingPetriSims = new PetriSimUtil.PetriSimUtilHelper[petriSimNames.length];
		for (int i = 0; i < petriSimNames.length; i++) {
			mergingPetriSims[i] = petriSimUtilHelpers.get(petriSimNames[i]);
		}

		return this;
	}

	/**
	 * Replace all PetriP by petriP from first PetriNet
	 *
	 * @param petriPNames names of PetriP from merged petri nets
	 */
	public PetriNetConnector merge(String... petriPNames) {
		if (mergingPetriSims == null) {
			throw new RuntimeException("Merge did not started");
		}
		if (mergingPetriSims.length != petriPNames.length) {
			throw new RuntimeException("Petri names count should match merging nets count");
		}

		PetriP mergePetriP = mergingPetriSims[0].getP(petriPNames[0]);

		for (int i = 1; i < petriPNames.length; i++) {
			mergingPetriSims[i].setP(petriPNames[i], mergePetriP);
		}

		return this;
	}

	/**
	 * Merge PetriPs from nets with names JointPName
	 *
	 * @param jointPName name of PetriP which all petri sims include
	 */
	public PetriNetConnector mergeSameName(String jointPName) {
		String[] jointNames = new String[mergingPetriSims.length];
		for (int i = 0; i < jointNames.length; i++) {
			jointNames[i] = jointPName;
		}
		return merge(jointNames);
	}

	public ArrayList<PetriSim> buildList() {
		return petriSims;
	}

	public PetriObjModel buildModel() {
		return new PetriObjModel(petriSims);
	}
}
