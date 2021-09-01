package org.firstclass.kahoot.resources;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author Loki
 * @date 10/08/21
 */
@ApplicationScoped
@Path("/rooms")
@Produces( MediaType.APPLICATION_JSON )
public class RoomResources
{
    private static final Logger LOGGER = Logger.getLogger( RoomResources.class );
    
    private final Set<Room> rooms;
    
    public RoomResources()
    {
        rooms = new HashSet<>();
        rooms.add( new Room("loki") );
        rooms.add( new Room("firstclass") );
        LOGGER.info( "Initialising rooms for loki and firstclass" );
    }
    
    @GET
    public Set<Room> getRooms()
    {
        LOGGER.info( String.format( "Listing rooms, room count: %d  ", rooms.size() ) );
        return rooms;
    }
    
    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    public Set<Room> addRoom(Room room)
    {
        if( rooms.stream().noneMatch( room1 -> Objects.equals( room1.getRoomId(), room.getRoomId() ) ) )
        {
            rooms.add( new Room(room.getRoomId()) );
            LOGGER.info( String.format( "Adding room: %s success ", room.getRoomId() ) );
        } else
        {
            LOGGER.info( String.format( "Adding room: %s fail. The room exists ", room.getRoomId() ) );
            throw new BadRequestException(String.format( "Adding room: %s fail. The room exists ", room.getRoomId() ));
        }
    
        return rooms;
    }
    
    @DELETE
    @Consumes( MediaType.APPLICATION_JSON )
    public Set<Room> deleteRoom(String roomId)
    {
        var result =  rooms.removeIf( existingRoom -> existingRoom.getRoomId().contentEquals( roomId ) );
    
        if( result )
        {
            LOGGER.info( String.format( "Delete room: %s success ", roomId ) );
        } else
        {
            LOGGER.info( String.format( "Delete room: %s fail ", roomId ) );
            throw new BadRequestException("Delete room: %s fail ");
        }
    
        return rooms;
    }
    
    @GET
    @Path( "{id}" )
    public Room getRoom(String id)
    {
        var room = rooms.stream().filter( room1->room1.getRoomId().equals( id ) ).findFirst().orElseGet( () -> null );
        if(room != null)
        {
            LOGGER.info( String.format( "Get room: %s success ", room.getRoomId() ) );
        } else
        {
            LOGGER.info( String.format( "Get room: %s fail ", id ) );
        }
        
        return room;
        
    }
    
    public Room getRoomForServer(String id)
    {
        
        return rooms.stream().filter( room->room.getRoomId().equals( id ) ).findFirst().orElseGet( () -> null );
        
    }
    
}
