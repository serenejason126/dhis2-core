package org.hisp.dhis.trackedentity.action.notification;

/*
 * Copyright (c) 2004-2017, University of Oslo
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * Neither the name of the HISP project nor the names of its contributors may
 * be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import com.opensymphony.xwork2.Action;
import org.hisp.dhis.common.ValueType;
import org.hisp.dhis.dataelement.DataElement;
import org.hisp.dhis.program.ProgramStage;
import org.hisp.dhis.program.ProgramStageService;
import org.hisp.dhis.trackedentity.TrackedEntityAttribute;
import org.hisp.dhis.user.UserGroup;
import org.hisp.dhis.user.UserGroupService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Halvdan Hoem Grelland
 */
public class ShowAddProgramStageNotificationAction
    implements Action
{
    private ProgramStageService programStageService;

    public void setProgramStageService( ProgramStageService programStageService )
    {
        this.programStageService = programStageService;
    }

    private UserGroupService userGroupService;

    public void setUserGroupService( UserGroupService userGroupService )
    {
        this.userGroupService = userGroupService;
    }

    // -------------------------------------------------------------------------
    // Input/Output
    // -------------------------------------------------------------------------

    private int id; // ID of ProgramStage

    public void setId( int id )
    {
        this.id = id;
    }

    private ProgramStage programStage;

    public ProgramStage getProgramStage()
    {
        return programStage;
    }

    private List<UserGroup> userGroups;

    public List<UserGroup> getUserGroups()
    {
        return userGroups;
    }

    private List<TrackedEntityAttribute> attributes;

    public List<TrackedEntityAttribute> getAttributes()
    {
        return attributes;
    }

    private List<DataElement> dataElements;

    public List<DataElement> getDataElements()
    {
        return dataElements;
    }

    private List<TrackedEntityAttribute> phoneNumberAttributes;

    public List<TrackedEntityAttribute> getPhoneNumberAttributes()
    {
        return phoneNumberAttributes;
    }

    private List<TrackedEntityAttribute> emailAttributes;

    public List<TrackedEntityAttribute> getEmailAttributes()
    {
        return emailAttributes;
    }

    private List<DataElement> phoneNumberDataElements;

    public List<DataElement> getPhoneNumberDataElements()
    {
        return phoneNumberDataElements;
    }

    private List<DataElement> emailDataElements;

    public List<DataElement> getEmailDataElements()
    {
        return emailDataElements;
    }

    // -------------------------------------------------------------------------
    // Action implementation
    // -------------------------------------------------------------------------

    @Override
    public String execute() throws Exception
    {
        programStage = programStageService.getProgramStage( id );
        userGroups = userGroupService.getAllUserGroups();
        attributes = programStage.getProgram().getTrackedEntityAttributes();
        dataElements = programStage.getProgramStageDataElements().stream()
            .map( psde -> psde.getDataElement() )
            .collect( Collectors.toList() );

        phoneNumberAttributes = getAttributeBasedOnValueType( attributes, ValueType.PHONE_NUMBER );
        emailAttributes = getAttributeBasedOnValueType( attributes, ValueType.EMAIL );

        phoneNumberDataElements = getDataElementBasedOnValueType( dataElements, ValueType.PHONE_NUMBER );
        emailDataElements = getDataElementBasedOnValueType( dataElements, ValueType.EMAIL );

        return SUCCESS;
    }

    private List<TrackedEntityAttribute> getAttributeBasedOnValueType( List<TrackedEntityAttribute> attributes, ValueType valueType )
    {
        return attributes.stream().filter( attr -> attr.getValueType().equals( valueType ) ).collect( Collectors.toList() );
    }

    private List<DataElement> getDataElementBasedOnValueType( List<DataElement> dataElements, ValueType valueType )
    {
        return dataElements.stream().filter( de -> de.getValueType().equals( valueType ) ).collect( Collectors.toList() );
    }
}
