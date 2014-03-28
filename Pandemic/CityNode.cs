using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Pandemic
{
    class CityNode
    {
        String name = "";
        List<CityNode> connectedCities = new List<CityNode>();
        public CityNode(String cityName)
        {
            name = cityName;
        }
    }
}
