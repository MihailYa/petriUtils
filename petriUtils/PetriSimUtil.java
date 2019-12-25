package petriUtils;

import PetriObj.PetriP;
import PetriObj.PetriSim;

import java.util.Objects;

public class PetriSimUtil {
	public static PetriSimUtilHelper createHelperForPetriSim(PetriSim petriSim) {
		return new PetriSimUtilHelper(petriSim.getNet()
		                                      .getListP());
	}

	public static PetriSimUtilHelper createHelperForList(PetriP[] petriPList) {
		return new PetriSimUtilHelper(petriPList);
	}

	public static Pair<Integer, PetriP> getPetriPWithIndex(PetriP[] petriPlist, String name) {
		for (int i = 0; i < petriPlist.length; i++) {
			PetriObj.PetriP petriP = petriPlist[i];
			if (petriP.getName()
			          .equals(name)) {
				return new Pair<>(i, petriP);
			}
		}

		return null;
	}

	public static PetriP getPetriP(PetriP[] petriPlist, String name) {
		return Objects.requireNonNull(getPetriPWithIndex(petriPlist, name))
		              .getValue();
	}

	public static void setPetriP(PetriP[] petriPlist, String name, PetriP newPetriP) {
		Pair<Integer, PetriP> integerPetriPPair = Objects.requireNonNull(getPetriPWithIndex(petriPlist, name));
		petriPlist[integerPetriPPair.getKey()] = newPetriP;
	}

	public static PetriP getPetriP(PetriSim petriSim, String name) {
		return getPetriP(petriSim.getNet().getListP(), name);
	}

	public static void setPetriP(PetriSim petriSim, String name, PetriP newPetriP) {
		setPetriP(petriSim.getNet().getListP(), name, newPetriP);
	}

	public static class PetriSimUtilHelper {
		private PetriP[] petriPlist;

		public PetriSimUtilHelper(PetriP[] petriPlist) {
			this.petriPlist = petriPlist;
		}

		public PetriP getP(String name) {
			return getPetriP(petriPlist, name);
		}

		public void setP(String name, PetriP newPetriP) {
			setPetriP(petriPlist, name, newPetriP);
		}
	}
}
