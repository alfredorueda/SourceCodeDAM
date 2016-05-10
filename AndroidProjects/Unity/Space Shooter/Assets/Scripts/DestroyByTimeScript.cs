using UnityEngine;
using System.Collections;

public class DestroyByTimeScript : MonoBehaviour {

	public float timelife;

	void Start () {
		Destroy (gameObject, timelife);	
	}
}
