package aly.kafka.local;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class SmplPartitioner implements Partitioner
{
	public SmplPartitioner(VerifiableProperties props)
	{
	}

	public int partition(Object key, int partNum)
	{
		return key.hashCode() % partNum;
	}

}
