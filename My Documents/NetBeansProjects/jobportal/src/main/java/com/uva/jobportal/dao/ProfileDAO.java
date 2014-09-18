/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uva.jobportal.dao;

import com.uva.jobportal.domain.Profile;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface ProfileDAO {

    public Profile viewProfile(int id);

    public Profile editProfile(Profile job);

    public List<Profile> searchProfileByText(String text);
}
