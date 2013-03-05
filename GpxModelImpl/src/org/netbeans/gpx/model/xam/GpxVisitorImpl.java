package org.netbeans.gpx.model.xam;

import javax.xml.namespace.QName;
import org.netbeans.gpx.model.entity.GpxModel.Schema;
import org.netbeans.gpx.model.entity.GpxModel.SchemaType;
import org.netbeans.modules.xml.xam.dom.AbstractDocumentComponent;
import org.w3c.dom.Element;

/**
 *
 * @author msc
 */
public class GpxVisitorImpl implements GpxVisitor {
    
    private Element element;
    
    private GpxDocumentModelImpl model;
    
    private GpxDocumentComponent result;
    
    public GpxVisitorImpl(GpxDocumentModelImpl model) {
        this.model = model;
    }
    
    void init(){
        result = null;
        model = null;
    }
    
    GpxDocumentComponent create(GpxDocumentComponent context, Element ele) {
        
        GpxDocumentComponent component = null;
        QName qName = AbstractDocumentComponent.getQName(ele);
        
        if (Schema.NAME_SPACE.toString().equals(qName.getNamespaceURI())) {
            
            if(context == null){
                component = new Gpx(model, ele);
            }else{
                component = result;
                
                element = ele;
                context.accept(this);
            }
        }
        return component;
    }
    
    private boolean isEqualToType(SchemaType type){
        return type.name().equals(element.getLocalName());
    }
    
    @Override
    public void visit(GpxType gpx) {
    }
    
    @Override
    public void visit(MetadataType metadata) {
    }
    
    @Override
    public void visit(CopyrightType copyright) {
    }
    
    @Override
    public void visit(EmailType email) {
    }
    
    @Override
    public void visit(LinkType link) {
    }
    
    @Override
    public void visit(PersonType person) {
    }
    
    @Override
    public void visit(BoundsType bounds) {
    }
    
    @Override
    public void visit(WaypointType wptType) {
    }
    
    @Override
    public void visit(TrackType trkType) {
    }
    
    @Override
    public void visit(TrackSegmentType trkSegType) {
    }
    
    @Override
    public void visit(ValueTextComponent textComponent) {
        if(isEqualToType(SchemaType.CMT)){
            result = new Comment(model, element);
        }else if(isEqualToType(SchemaType.DGPSID)){
            result = new DGpsId(model, element);
        }else if(isEqualToType(SchemaType.DESC)){
            result = new Description(model, element);
        }else if(isEqualToType(SchemaType.ELE)){
            result = new Elevation(model, element);
        }else if(isEqualToType(SchemaType.FIX)){
            result = new Fix(model, element);
        }else if(isEqualToType(SchemaType.HDOP)){
            result = new Hdop(model, element);
        }else if(isEqualToType(SchemaType.KEYWORDS)){
            result = new KeyWords(model, element);
        }else if(isEqualToType(SchemaType.LICENSE)){
            result = new License(model, element);
        }else if(isEqualToType(SchemaType.MAGVAR)){
            result = new Magvar(model, element);
        }else if(isEqualToType(SchemaType.NAME)){
            result = new Name(model, element);
        }else if(isEqualToType(SchemaType.NUMBER)){
            result = new Number(model, element);
        }else if(isEqualToType(SchemaType.PDOP)){
            result = new Pdop(model, element);
        }else if(isEqualToType(SchemaType.SAT)){
            result = new Satellite(model, element);
        }else if(isEqualToType(SchemaType.SRC)){
            result = new Source(model, element);
        }else if(isEqualToType(SchemaType.SYM)){
            result = new Symbol(model, element);
        }else if(isEqualToType(SchemaType.TEXT)){
            result = new Text(model, element);
        }else if(isEqualToType(SchemaType.TIME)){
            result = new Time(model, element);
        }else if(isEqualToType(SchemaType.TYPE)){
            result = new Type(model, element);
        }else if(isEqualToType(SchemaType.VDOP)){
            result = new Vdop(model, element);
        }else if(isEqualToType(SchemaType.YEAR)){
            result = new Year(model, element);
        }
    }
}
