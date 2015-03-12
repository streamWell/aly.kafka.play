package aly.kafka.tranform;

import java.util.ArrayList;
import java.util.List;

import aly.kafka.obu.msg.FldNameType;
import aly.kafka.obu.msg.IHandler;
import aly.kafka.obu.msg.MetaField;
import aly.kafka.obu.msg.StrValueExtractor;


// List<E> extends Collection<E>
public class TransTestPriceDetails implements ITransormer
{
/**
 * This is a test transformer  
 * 
 * The message (payload) should have the following structure:
 * The  has a structure : 
 * 
 * 	<payload> := <header>,<body>
 *  <header> := <storID>,<transformerID>,<loaderID>
 *  <body := string,string,...  
 */
	public static final int handlerID = 10;
	
	private static final List<FldNameType> fldNamesTypes;
	
	static
	{
		fldNamesTypes = new ArrayList<FldNameType>();
		
		FldNameType nametype = FldNameType.create("fld1", IHandler.FldTypesEnum.E_STRING);
		fldNamesTypes.add(nametype);
		
		nametype = FldNameType.create("fld2", IHandler.FldTypesEnum.E_INT);
		fldNamesTypes.add(nametype);
		
		nametype = FldNameType.create("fld3", IHandler.FldTypesEnum.E_DOUBLE);
		fldNamesTypes.add(nametype);
	}
	
	public TransTestPriceDetails() {}
	
	@Override
	public int getHandlerID()
	{
		return handlerID;
	}
	
	@Override
	public List<MetaField> transform(String payload)
	{
		List<MetaField> metaFldLst = new ArrayList<>();
		
		String[] elems = payload.split(",");
		int storeID = Integer.parseInt(elems[0]);
		int transformerID = Integer.parseInt(elems[1]);
		int loaderID = Integer.parseInt(elems[2]);

		int posInElem = 3;
		for(FldNameType nameType : fldNamesTypes)
		{
			String fldName = nameType.getFldName();
			String fldAsStr = elems[posInElem++];
			FldTypesEnum eTypeHint = nameType.geteTypeHint();
			Object value = StrValueExtractor.extract(fldAsStr, eTypeHint);
			MetaField metaFld = MetaField.create(fldName, elems[posInElem++], eTypeHint);
		}
		return metaFldLst;
	}
}
