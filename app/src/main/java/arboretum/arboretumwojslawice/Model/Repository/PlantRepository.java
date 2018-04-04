package arboretum.arboretumwojslawice.Model.Repository;

import java.util.ArrayList;
import java.util.List;

import arboretum.arboretumwojslawice.Model.businessentity.Plant;
import arboretum.arboretumwojslawice.R;

/**
 * Created by Komputer on 2018-03-24.
 */

public class PlantRepository {

    public List<Plant> getPlantsForMichal() {
        List<Plant> plants1 = new ArrayList<>();
        Plant route = new Plant(1, "Rodzaj1", "Gatunek1", R.drawable.flower1);
        plants1.add(route);
        route = new Plant(2, "Rodzaj2", "Gatunek2", R.drawable.flower2);
        plants1.add(route);
        route = new Plant(3, "Rodzaj3", "Gatunek3", R.drawable.flower3);
        plants1.add(route);

        return plants1;
    }

}
