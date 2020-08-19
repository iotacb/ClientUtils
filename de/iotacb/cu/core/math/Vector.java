package de.iotacb.cu.core.math;

import java.util.concurrent.ThreadLocalRandom;

public class Vector {

	public double x, y, z;
	
	void init(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector() {}

	public Vector(final double x, final double y) {
		init(x, y, 0);
	}
	
	public Vector(final double x, final double y, final double z) {
		init(x, y, z);
	}
	
	public Vector(final Vector vector) {
		init(vector.x, vector.y, vector.z);
	}
	
	public static final Vector random() {
		final double randNumber = Math.random() * (Math.PI * 2);
		return new Vector(Math.cos(randNumber), Math.sin(randNumber));
	}
	
	public static final Vector random(final double xMin, final double xMax, final double yMin, final double yMax) {
		final double x = ThreadLocalRandom.current().nextDouble(xMin, xMax);
		final double y = ThreadLocalRandom.current().nextDouble(yMin, yMax);
		return new Vector(x, y);
	}
	
	public static final Vector random(final double xMin, final double xMax, final double yMin, final double yMax, final double zMin, final double zMax) {
		final double x = ThreadLocalRandom.current().nextDouble(xMin, xMax);
		final double y = ThreadLocalRandom.current().nextDouble(yMin, yMax);
		final double z = ThreadLocalRandom.current().nextDouble(zMin, zMax);
		return new Vector(x, y, z);
	}
	
	public final Vector randomize(final double xMin, final double xMax, final double yMin, final double yMax) {
		set(Vector.random(xMin, xMax, yMin, yMax));
		return this;
	}
	
	public final Vector randomize(final double xMin, final double xMax, final double yMin, final double yMax, final double zMin, final double zMax) {
		set(Vector.random(xMin, xMax, yMin, yMax, zMin, zMax));
		return this;
	}
	
	public final Vector set(final double x, final double y) {
		this.x = x;
		this.y = y;
		return this;
	}
	
	public final Vector set(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}
	
	public final Vector set(final Vector vector) {
		this.x = vector.x;
		this.y = vector.y;
		this.z = vector.z;
		return this;
	}
	
	public final Vector add(final double x, final double y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	public final Vector add(final double x, final double y, final double z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}
	
	public final Vector add(final Vector vector) {
		this.x += vector.x;
		this.y += vector.y;
		this.z += vector.z;
		return this;
	}
	
	public final Vector sub(final double x, final double y) {
		this.x -= x;
		this.y -= y;
		return this;
	}
	
	public final Vector sub(final double x, final double y, final double z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}
	
	public final Vector sub(final Vector vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		this.z -= vector.z;
		return this;
	}
	
	public final Vector mul(final double x, final double y) {
		this.x *= x;
		this.y *= y;
		return this;
	}
	
	public final Vector mul(final double x, final double y, final double z) {
		this.x *= x;
		this.y *= y;
		this.z *= z;
		return this;
	}
	
	public final Vector mul(final Vector vector) {
		this.x *= vector.x;
		this.y *= vector.y;
		this.z *= vector.z;
		return this;
	}
	
	public final Vector div(final double x, final double y) {
		this.x /= x;
		this.y /= y;
		return this;
	}
	
	public final Vector div(final double x, final double y, final double z) {
		this.x /= x;
		this.y /= y;
		this.z /= z;
		return this;
	}
	
	public final Vector div(final Vector vector) {
		this.x /= vector.x;
		this.y /= vector.y;
		this.z /= vector.z;
		return this;
	}
	
	public final double dot(final double x, final double y) {
		return this.x * x + this.y * y;
	}
	
	public final double dot(final double x, final double y, final double z) {
		return this.x * x + this.y * y + this.z * z;
	}
	
	public final double dot(final Vector vector) {
		return dot(vector.x, vector.y, vector.z);
	}
	
	public final double cross(final double x, final double y) {
		return this.x * y - this.y * x;
	}
	
	public final Vector cross(final double x, final double y, final double z) {
		final double xCross = this.y * z - this.z * y;
		final double yCross = this.z * x - this.x * z;
		final double zCross = this.x * y - this.y * x;
		return new Vector(xCross, yCross, zCross);
	}
	
	public final Vector cross(final Vector vector) {
		return cross(vector.x, vector.y, vector.z);
	}
	
	public final Vector fromAngle(final double angle) {
		final double x = Math.cos(angle);
		final double y = Math.sin(angle);
		final double z = Math.tan(angle);
		return new Vector(x, y, z);
	}
	
	public final Vector normalize() {
		final double magnitude = getMagnitude();
		if (magnitude != 0 && magnitude != 1) {
			div(magnitude, magnitude, magnitude);
		}
		return this;
	}
	
	public final Vector limit(final double limit) {
		if (getMagnitude() > limit * limit) {
			normalize();
			mul(limit, limit, limit);
		}
		return this;
	}
	
	public final double getMagnitude() {
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	public final double getMagnitudeSq() {
		return x * x + y * y + z * z;
	}
	
	public final Vector setMagnitude(final double magnitude) {
		normalize();
		mul(magnitude, magnitude, magnitude);
		return this;
	}
	
	public final Vector clamp(final double xMin, final double xMax, final double yMin, final double yMax) {
		this.x = (x < xMin ? xMin : x > xMax ? xMax : x);
		this.y = (y < yMin ? yMin : y > yMax ? yMax : y);
		return this;
	}
	
	public final Vector clamp(final double xMin, final double xMax, final double yMin, final double yMax, final double zMin, final double zMax) {
		this.x = (x < xMin ? xMin : x > xMax ? xMax : x);
		this.y = (y < yMin ? yMin : y > yMax ? yMax : y);
		this.z = (z < zMin ? zMin : z > zMax ? zMax : z);
		return this;
	}
	
	public final Vector clamp(final Vector min, final Vector max) {
		this.x = (x < min.x ? min.x : x > max.x ? max.x : x);
		this.y = (y < min.y ? min.y : y > max.y ? max.y : y);
		this.z = (z < min.z ? min.z : z > max.z ? max.z : z);
		return this;
	}
	
	@Override
	public final Vector clone() {
		return new Vector(this);
	}
	
	public final Vector mirror() {
		return new Vector(x * -1, y * -1, z * -1);
	}
	
	public final Vector abs() {
		return new Vector(Math.abs(x), Math.abs(y), Math.abs(z));
	}
	
	public final Vector nabs() {
		return new Vector(Math.abs(x) * -1, Math.abs(y) * -1, Math.abs(z) * -1);
	}
	
	public final Vector center() {
		return new Vector(x / 2, y / 2, z / 2);
	}
	
	public final Vector zero() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
		return this;
	}
	
	public final double direction() {
		return Math.atan2(y, x);
	}
	
	public final double distance(final double x, final double y) {
		final double xDiff = this.x - x;
		final double yDiff = this.y - y;
		return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
	}
	
	public final double distance(final double x, final double y, final double z) {
		final double xDiff = this.x - x;
		final double yDiff = this.y - y;
		final double zDiff = this.z - z;
		return Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff + zDiff);
	}
	
	public final double distance(final Vector vector) {
		return distance(vector.x, vector.y, vector.z);
	}
	
	public final boolean equal(final double x, final double y) {
		return this.x == x && this.y == y;
	}
	
	public final boolean equal(final double x, final double y, final double z) {
		return this.x == x && this.y == y && this.z == z;
	}
	
	public final boolean equal(final Vector vector) {
		return equal(vector.x, vector.y, vector.z);
	}
	
	public final double getX() {
		return this.x;
	}
	
	public final double getY() {
		return this.y;
	}
	
	public final double getZ() {
		return this.z;
	}
	
	public final float getXf() {
		return (float) this.getX();
	}
	
	public final float getYf() {
		return (float) this.getY();
	}
	
	public final float getZf() {
		return (float) this.getZ();
	}
	
	public final int getXi() {
		return (int) this.getX();
	}
	
	public final int getYi() {
		return (int) this.getY();
	}
	
	public final int getZi() {
		return (int) this.getZ();
	}
	
	@Override
	public final String toString() {
		return String.format("{%s, %s, %s}", x, y, z);
	}

}