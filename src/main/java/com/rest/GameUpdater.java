package com.rest;

import com.dao.GameDao;
import com.dao.UserDao;
import com.domain.Game;
import com.domain.Wish;
import com.service.SteamConnector;

import javax.inject.Inject;
import javax.naming.NamingException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import java.io.IOException;
import java.util.List;

/**
 * Created by akrantan on 5.4.2017.
 */
@Path("/updateGamesForUser")
public class GameUpdater {

    @Inject
    private SteamConnector steamConnector;



    @PUT
    public void updateGamesForUser() throws IOException, NamingException {

    }

}



