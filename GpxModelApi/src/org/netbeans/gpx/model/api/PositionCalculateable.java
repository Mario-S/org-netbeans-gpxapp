package org.netbeans.gpx.model.api;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author msc
 */
public interface PositionCalculateable {

    /**
     * Calculates the centroid for the given positions.<br/> The method return a position with onlylatitude and
     * longitude set.
     *
     * @param positions collection of positions.
     * @return the centroid in a 2D plane.
     */
    Position getCentroid(Collection<? extends Position> positions);

    /**
     * Calculates the sum of the distances between the positions.
     *
     * @param positions a collection of positions.
     * @return the total distance
     */
    double getDistance(List<? extends Position> positions);

    /**
     * Calculates the distance between two positions.
     *
     * @param from the start position
     * @param to the end position
     * @return distanc
     */
    double getDistance(Position from, Position to);
    
}
