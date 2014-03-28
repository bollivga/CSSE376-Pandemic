using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Pandemic
{
    class CardDeck : CardStorage
    {
        Stack<Card> stored;
        public CardDeck()
        {
            stored = new Stack<Card>();


        }

    }
}
