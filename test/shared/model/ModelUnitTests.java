package shared.model;

public class ModelUnitTests {

	public static void main(String[] args) {
		
		String[] testClasses = new String[] {
				"shared.model.player.ActivePlayerFacadeTest",
				"shared.model.player.CitiesTest",
				"shared.model.player.InactivePlayerFacadeTest",
				"shared.model.player.LargestArmyTest",
				"shared.model.player.LongestRoadTest",
				"shared.model.player.RoadsTest",
				"shared.model.player.SettlementsTest",
				"shared.model.player.VictoryPointsTest",
				"shared.model.bank.BankTest",
				"shared.model.bank.PlayerBankTest"
		};

		org.junit.runner.JUnitCore.main(testClasses);
	}

}
