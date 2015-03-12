package aly.kafka.obu.msg;

import java.util.List;

import aly.kafka.obu.msg.IHandler.FldTypesEnum;

public interface IHandler
{
	enum FldTypesEnum {E_STRING, E_INT, E_DOUBLE, E_JSON};
	
	int getHandlerID();
}
