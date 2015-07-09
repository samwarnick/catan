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
				"shared.model.bank.PlayerBankTest",
				"test.client.ClientCommunicatorTest",
				"test.client.MoveTest",
				"test.client.GameTests",
				"test.client.GamesTest",
				"test.client.RegisterUserTest",
				"test.shared.model.BoardFacadeTest",
				"test.shared.model.BoardTest",
				"test.shared.model.GameModelFacadeTests",
				"test.shared.model.JsonParserTest",
				"test.shared.model.RobberTest",
				"test.shared.model.TradeRatioTest"
		};

		org.junit.runner.JUnitCore.main(testClasses);
	}

}
