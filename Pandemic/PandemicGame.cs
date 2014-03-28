using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Pandemic
{
    class PandemicGame
    {
        
        CardStorage p1Hand = new CardHand();
        CardStorage infectionDiscard = new CardDiscard();
        CardStorage playerDeck = new CardDeck();
        CardStorage playerDiscard = new CardDiscard();
        Player p1 = new Player(0);
        CityGraph world = new CityGraph();
    }
}
