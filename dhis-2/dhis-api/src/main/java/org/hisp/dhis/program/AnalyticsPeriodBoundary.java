package org.hisp.dhis.program;

import java.util.Objects;

import org.hisp.dhis.common.BaseIdentifiableObject;
import org.hisp.dhis.common.DxfNamespaces;
import org.hisp.dhis.common.EmbeddedObject;
import org.hisp.dhis.common.adapter.JacksonPeriodTypeDeserializer;
import org.hisp.dhis.common.adapter.JacksonPeriodTypeSerializer;
import org.hisp.dhis.period.PeriodType;
import org.hisp.dhis.schema.PropertyType;
import org.hisp.dhis.schema.annotation.Property;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

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

/**
 * @author Markus Bekken
 */
@JacksonXmlRootElement( localName = "analyticsPeriodBoundary", namespace = DxfNamespaces.DXF_2_0 )
public class AnalyticsPeriodBoundary extends BaseIdentifiableObject implements EmbeddedObject
{
    public static String EVENT_DATE = "EVENT_DATE";
    public static String ENROLLMENT_DATE = "ENROLLMENT_DATE";
    public static String INCIDENT_DATE = "INCIDENT_DATE";
    
    private String boundaryTarget;
    
    private AnalyticsPeriodBoundaryType analyticsPeriodBoundaryType;
    
    private PeriodType offsetPeriodType;
    
    private Integer offsetNumberOfPeriods;
    
    private ProgramIndicator programIndicator;
    
    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------
    
    public AnalyticsPeriodBoundary()
    {
        
    }
    
    public AnalyticsPeriodBoundary( String boundaryTarget, AnalyticsPeriodBoundaryType analyticsPeriodBoundaryType, 
        PeriodType offsetPeriodType, Integer offsetNumberOfPeriods )
    {
        this.boundaryTarget = boundaryTarget;
        this.analyticsPeriodBoundaryType = analyticsPeriodBoundaryType;
        this.offsetPeriodType = offsetPeriodType;
        this.offsetNumberOfPeriods = offsetNumberOfPeriods;
    }
    
    // -------------------------------------------------------------------------
    // Overrides
    // -------------------------------------------------------------------------
  
    @Override
    public int hashCode()
    {
        return 31 * super.hashCode() + Objects.hash( this.boundaryTarget, this.analyticsPeriodBoundaryType, this.offsetPeriodType, this.offsetNumberOfPeriods );
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
        {
            return true;
        }
        if ( obj == null || getClass() != obj.getClass() )
        {
            return false;
        }
        if ( !super.equals( obj ) )
        {
            return false;
        }

        final AnalyticsPeriodBoundary other = (AnalyticsPeriodBoundary) obj;

        return Objects.equals( this.boundaryTarget, other.boundaryTarget )
            && Objects.equals( this.analyticsPeriodBoundaryType, other.analyticsPeriodBoundaryType )
            && Objects.equals( this.offsetPeriodType, other.offsetPeriodType )
            && Objects.equals( this.offsetNumberOfPeriods, other.offsetNumberOfPeriods );
    }

    // -------------------------------------------------------------------------
    // Getters and setters
    // -------------------------------------------------------------------------
    
    @JsonProperty
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public String getBoundaryTarget()
    {
        return boundaryTarget;
    }

    public void setBoundaryTarget( String boundaryTarget )
    {
        this.boundaryTarget = boundaryTarget;
    }
    
    @JsonProperty
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public AnalyticsPeriodBoundaryType getAnalyticsPeriodBoundaryType()
    {
        return analyticsPeriodBoundaryType;
    }

    public void setAnalyticsPeriodBoundaryType( AnalyticsPeriodBoundaryType analyticsPeriodBoundaryType )
    {
        this.analyticsPeriodBoundaryType = analyticsPeriodBoundaryType;
    }
    
    @JsonProperty
    @JsonSerialize( using = JacksonPeriodTypeSerializer.class )
    @JsonDeserialize( using = JacksonPeriodTypeDeserializer.class )
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    @Property( PropertyType.TEXT )
    public PeriodType getOffsetPeriodType()
    {
        return offsetPeriodType;
    }

    public void setOffsetPeriodType( PeriodType offsetPeriodType )
    {
        this.offsetPeriodType = offsetPeriodType;
    }
    
    @JsonProperty
    @JacksonXmlProperty( namespace = DxfNamespaces.DXF_2_0 )
    public Integer getOffsetNumberOfPeriods()
    {
        return offsetNumberOfPeriods;
    }

    public void setOffsetNumberOfPeriods( Integer offsetNumberOfPeriods )
    {
        this.offsetNumberOfPeriods = offsetNumberOfPeriods;
    }
    
    @Property( owner = Property.Value.FALSE )
    public ProgramIndicator getProgramIndicator()
    {
        return programIndicator;
    }

    public void setProgramIndicator( ProgramIndicator programIndicator )
    {
        this.programIndicator = programIndicator;
    }
}
