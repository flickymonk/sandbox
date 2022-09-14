package com.alevel.sandbox.algorithms;

import java.util.*;

public interface UniqueObjectsOrdered {

    List<Integer> findUniqueIndices(List<?> objects);

}


class UniqueObjectsOrderedBruteForce implements UniqueObjectsOrdered {


    @Override
    public List<Integer> findUniqueIndices(List<?> objects) {
        List<Integer> unique = new ArrayList<>();
        outer:
        for (var i = objects.listIterator(); i.hasNext(); ) {
            int i1 = i.nextIndex();
            var obj1 = i.next();
            for (var j = objects.listIterator(); j.hasNext(); ) {
                int i2 = j.nextIndex();
                var obj2 = j.next();
                if (i1 != i2 && Objects.equals(obj1, obj2)) {
                    continue outer;
                }
            }
            unique.add(i1);
        }
        return unique;
    }
}

class UniqueObjectsOrderedAdditionalSpace implements UniqueObjectsOrdered {

    @Override
    public List<Integer> findUniqueIndices(List<?> objects) {

        Map<Object, Integer> occurrences =  new HashMap<>();

        for (Object object : objects) {
            occurrences.merge(object, 1, Integer::sum);
        }

        List<Integer> unique = new ArrayList<>();
        for (var i = objects.listIterator(); i.hasNext(); ) {
            int idx = i.nextIndex();
            var obj = i.next();
            if (occurrences.get(obj) == 1) {
                unique.add(idx);
            }
        }
        return unique;
    }
}

class UniqueObjectsOrderedAdditionalSpaceOneLoop implements UniqueObjectsOrdered {

    @Override
    public List<Integer> findUniqueIndices(List<?> objects) {
        Map<Object, Integer> occurrences =  new HashMap<>();
        Set<Integer> uniqueIndices = new LinkedHashSet<>();

        for (var i = objects.listIterator(); i.hasNext(); ) {
            int idx = i.nextIndex();
            var obj = i.next();
            switch (occurrences.merge(obj, 1, Integer::sum)) {
                case 1 -> uniqueIndices.add(idx);
                case 2 -> uniqueIndices.remove(idx);
            }
        }
        return new ArrayList<>(uniqueIndices);
    }
}