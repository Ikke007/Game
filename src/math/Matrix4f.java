package math;
import static java.lang.Math.*;

import java.nio.FloatBuffer;
import util.BufferUtils;

public class Matrix4f {

	
	public final static int SIZE = 4 * 4;
	public float[] elements = new float[SIZE];
	
	public Matrix4f() {
	}
	
	public static Matrix4f identity() {
		Matrix4f result = new Matrix4f();
		for (int i = 0; i < SIZE; i++)
			result.elements[i] = 0.0f;
		
		/* [x + y * 4]  x=row  y=column	4=Breite der Matrix
		 * Identitäts-Matrix
		 * 1	0	0	0
		 * 0	1	0	0
		 * 0	0	1	0
		 * 0	0	0	1
		 */
		result.elements[0 + 0 * 4] = 1.0f;
		result.elements[1 + 1 * 4] = 1.0f;
		result.elements[2 + 2 * 4] = 1.0f;
		result.elements[3 + 3 * 4] = 1.0f;
		
		return result;
	}
	
	//
	
	/*
	 * 	2/(r-l)		0		0			(r+l)/(r-l)
	 * 	0			2/(t-b)	0			(t+b)/(t-b)
	 * 	0			0		-2/(f-n)	(f+n)/(f-n)
	 * 	0			0		0			1
	 */
	public static Matrix4f orthographic( float left, float right, float bottom, float top, float near, float far){
		Matrix4f result = identity();
		
		result.elements[0 + 0 * 4] = 2.0f / (right - left);
		
		result.elements[1 + 1 * 4] = 2.0f / (top - bottom);
		
		result.elements[2 + 2 * 4] = 2.0f / (near - far);
		
		result.elements[0 + 3 * 4] = (left + right) / (left - right);
		result.elements[1 + 3 * 4] = (bottom + top) / (bottom - top);
		result.elements[2 + 3 * 4] = (far + near) / (far - near);
		
		return result;
	}
	
	/*Translations-Matrix
	 * 	1	0	0	x
	 * 	0	1	0	y
	 * 	0	0	1	z
	 * 	0	0	0	1
	*/
	public static Matrix4f translate(Vector3f vector){
		Matrix4f result = identity();
		
		result.elements[0 + 3 * 4] = vector.x; // 
		result.elements[1 + 3 * 4] = vector.y;
		result.elements[2 + 3 * 4] = vector.z;
		
		return result;
	}
	
	
	/* Rotations-Matrix
	 * cos(x)	-sin(x)	0	0
	 * sin(x)	cos(x)	0	0
	 * 0		0		1	0
	 * 0		0		0	1
	*/	
	public static Matrix4f rotate(float angle) {
		
		Matrix4f result = identity();
		float radiant = (float) toRadians(angle);
		float cos = (float) cos(radiant);
		float sin = (float) sin(radiant);
		
		result.elements[0 + 0 * 4] = cos;
		result.elements[1 + 0 * 4] = sin;
		result.elements[0 + 1 * 4] = -sin;
		result.elements[1 + 1 * 4] = cos;
		
		return result;
	}
	
	//[y + x * 4]  y=row  x=column	4=Breite der Matrix
	public Matrix4f multiply(Matrix4f matrix){
		Matrix4f result = new Matrix4f();
		for (int y = 0; y < 4; y++){
			for(int x = 0; x < 4; x++){
				float sum = 0.0f;
				for (int e = 0; e < 4; e++){
					sum += this.elements[x + e * 4] * matrix.elements[e + y * 4];
				}
				result.elements[x + y * 4] = sum;
			}
		}
		return result;
	}
	
	public FloatBuffer toFloatBuffer() {
		return BufferUtils.createFloatBuffer(elements);
	}
}
