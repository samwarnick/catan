import org.junit.* ;
import static org.junit.Assert.* ;

public class AllTests {
	
	@Before
	public void setup() {
	}
	
	@After
	public void teardown() {
	}
	
	@Test
	public void test_1() {
		assertEquals("OK", "OK");
		assertTrue(true);
		assertFalse(false);
	}

	public static void main(String[] args) {
		
		String[] testClasses = new String[] {
				"client.poller.PollerTest",
				"client.proxy.ClientCommunicatorTest",
				"client.proxy.GamesTest",
				"client.proxy.GameTests",
				"client.proxy.MoveTest",
				"client.proxy.RegisterUserTest",
				"shared.model.bank.BankTest",
				"shared.model.bank.PlayerBankTest",
				"shared.model.board.BoardFacadeTest",
				"shared.model.board.RobberTest",
				"shared.model.player.ActivePlayerFacadeTest",
				"shared.model.player.CitiesTest",
				"shared.model.player.InactivePlayerFacadeTest",
				"shared.model.player.LargestArmyTest",
				"shared.model.player.LongestRoadTest",
				"shared.model.player.RoadsTest",
				"shared.model.player.SettlementsTest",
				"shared.model.player.TradeRatioTest",
				"shared.model.player.VictoryPointsTest",
				"shared.model.GameModelFacadeTests",
				"shared.model.JsonParserTest",
				"server.commands.games.CreateCommandTest",
				"server.commands.games.JoinCommandTest",
				"server.commands.games.ListCommandTest",
				"server.commands.move.BuyDevCardCommandTest",
				"server.commands.move.MaritimeTradeInputTest",
				"server.commands.move.AcceptTradeCommandTest",
				"server.commands.move.DiscardCardsTest",
				"server.commands.move.OfferTradeCommandTest",
				"server.commands.move.FinishTurnTest",
				"server.commands.move.SendChatCommandTest"
		};

		org.junit.runner.JUnitCore.main(testClasses);
	}
	
}


