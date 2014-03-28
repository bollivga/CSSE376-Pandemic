using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Pandemic
{
    class CardDiscard : CardStorage
    {
        List<Card> stored;
        public CardDiscard()
        {
            stored = new List<Card>();
        }

    }
}
