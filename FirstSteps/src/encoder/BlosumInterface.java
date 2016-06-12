package encoder;

import encoder.AAInterface.AminoAcid;
import encoder.BlosumEncoder.*;

public interface BlosumInterface {

	public static final char[] AAOrder = {'A','R','N','D','C','Q','E','G','H','I','L','K','M','F','P','S','T','W','Y','V','B','Z','X','*'};

	public static final int[][] b30Matrix =
		{
				{ 4, -1,  0,  0, -3,  1,  0,  0, -2,  0, -1,  0,  1, -2, -1,  1,  1, -5, -4,  1,  0,  0,  0, -7},
				{-1,  8, -2, -1, -2,  3, -1, -2, -1, -3, -2,  1,  0, -1, -1, -1, -3,  0,  0, -1, -2,  0, -1, -7},
				{ 0, -2,  8,  1, -1, -1, -1,  0, -1,  0, -2,  0,  0, -1, -3,  0,  1, -7, -4, -2,  4, -1,  0, -7},
				{ 0, -1,  1,  9, -3, -1,  1, -1, -2, -4, -1,  0, -3, -5, -1,  0, -1, -4, -1, -2,  5,  0, -1, -7},
				{-3, -2, -1, -3, 17, -2,  1, -4, -5, -2,  0, -3, -2, -3, -3, -2, -2, -2, -6, -2, -2,  0, -2, -7},
				{ 1,  3, -1, -1, -2,  8,  2, -2,  0, -2, -2,  0, -1, -3,  0, -1,  0, -1, -1, -3, -1,  4,  0, -7},
				{ 0, -1, -1,  1,  1,  2,  6, -2,  0, -3, -1,  2, -1, -4,  1,  0, -2, -1, -2, -3,  0,  5, -1, -7},
				{ 0, -2,  0, -1, -4, -2, -2,  8, -3, -1, -2, -1, -2, -3, -1,  0, -2,  1, -3, -3,  0, -2, -1, -7},
				{-2, -1, -1, -2, -5,  0,  0, -3, 14, -2, -1, -2,  2, -3,  1, -1, -2, -5,  0, -3, -2,  0, -1, -7},
				{ 0, -3,  0, -4, -2, -2, -3, -1, -2,  6,  2, -2,  1,  0, -3, -1,  0, -3, -1,  4, -2, -3,  0, -7},
				{-1, -2, -2, -1,  0, -2, -1, -2, -1,  2,  4, -2,  2,  2, -3, -2,  0, -2,  3,  1, -1, -1,  0, -7},
				{ 0,  1,  0,  0, -3,  0,  2, -1, -2, -2, -2,  4,  2, -1,  1,  0, -1, -2, -1, -2,  0,  1,  0, -7},
				{ 1,  0,  0, -3, -2, -1, -1, -2,  2,  1,  2,  2,  6, -2, -4, -2,  0, -3, -1,  0, -2, -1,  0, -7},
				{-2, -1, -1, -5, -3, -3, -4, -3, -3,  0,  2, -1, -2, 10, -4, -1, -2,  1,  3,  1, -3, -4, -1, -7},
				{-1, -1, -3, -1, -3,  0,  1, -1,  1, -3, -3,  1, -4, -4, 11, -1,  0, -3, -2, -4, -2,  0, -1, -7},
				{ 1, -1,  0,  0, -2, -1,  0,  0, -1, -1, -2,  0, -2, -1, -1,  4,  2, -3, -2, -1,  0, -1,  0, -7},
				{ 1, -3,  1, -1, -2,  0, -2, -2, -2,  0,  0, -1,  0, -2,  0,  2,  5, -5, -1,  1,  0, -1,  0, -7},
				{-5,  0, -7, -4, -2, -1, -1,  1, -5, -3, -2, -2, -3,  1, -3, -3, -5, 20,  5, -3, -5, -1, -2, -7},
				{-4,  0, -4, -1, -6, -1, -2, -3,  0, -1,  3, -1, -1,  3, -2, -2, -1,  5,  9,  1, -3, -2, -1, -7},
				{ 1, -1, -2, -2, -2, -3, -3, -3, -3,  4,  1, -2,  0,  1, -4, -1,  1, -3,  1,  5, -2, -3,  0, -7},
				{ 0, -2,  4,  5, -2, -1,  0,  0, -2, -2, -1,  0, -2, -3, -2,  0,  0, -5, -3, -2,  5,  0, -1, -7},
				{ 0,  0, -1,  0,  0,  4,  5, -2,  0, -3, -1,  1, -1, -4,  0, -1, -1, -1, -2, -3,  0,  4,  0, -7},
				{ 0, -1,  0, -1, -2,  0, -1, -1, -1,  0,  0,  0,  0, -1, -1,  0,  0, -2, -1,  0, -1,  0, -1, -7},
				{-7, -7, -7, -7, -7, -7, -7, -7, -7, -7, -7, -7, -7, -7, -7, -7, -7, -7, -7, -7, -7, -7, -7,  1},
		};

	public static final int[][] b35Matrix =
		{
				{ 5, -1, -1, -1, -2,  0, -1,  0, -2, -1, -2,  0,  0, -2, -2,  1,  0, -2, -1,  0, -1, -1,  0, -5},
				{-1,  8, -1, -1, -3,  2, -1, -2, -1, -3, -2,  2,  0, -1, -2, -1, -2,  0,  0, -1, -1,  0, -1, -5},
				{-1, -1,  7,  1, -1,  1, -1,  1,  1, -1, -2,  0, -1, -1, -2,  0,  0, -2, -2, -2,  4,  0,  0, -5},
				{-1, -1,  1,  8, -3, -1,  2, -2,  0, -3, -2, -1, -3, -3, -1, -1, -1, -3, -2, -2,  5,  1, -1, -5},
				{-2, -3, -1, -3, 15, -3, -1, -3, -4, -4, -2, -2, -4, -4, -4, -3, -1, -5, -5, -2, -2, -2, -2, -5},
				{ 0,  2,  1, -1, -3,  7,  2, -2, -1, -2, -2,  0, -1, -4,  0,  0,  0, -1,  0, -3,  0,  4, -1, -5},
				{-1, -1, -1,  2, -1,  2,  6, -2, -1, -3, -1,  1, -2, -3,  0,  0, -1, -1, -1, -2,  0,  5, -1, -5},
				{ 0, -2,  1, -2, -3, -2, -2,  7, -2, -3, -3, -1, -1, -3, -2,  1, -2, -1, -2, -3,  0, -2, -1, -5},
				{-2, -1,  1,  0, -4, -1, -1, -2, 12, -3, -2, -2,  1, -3, -1, -1, -2, -4,  0, -4,  0, -1, -1, -5},
				{-1, -3, -1, -3, -4, -2, -3, -3, -3,  5,  2, -2,  1,  1, -1, -2, -1, -1,  0,  4, -2, -3,  0, -5},
				{-2, -2, -2, -2, -2, -2, -1, -3, -2,  2,  5, -2,  3,  2, -3, -2,  0,  0,  0,  2, -2, -2,  0, -5},
				{ 0,  2,  0, -1, -2,  0,  1, -1, -2, -2, -2,  5,  0, -1,  0,  0,  0,  0, -1, -2,  0,  1,  0, -5},
				{ 0,  0, -1, -3, -4, -1, -2, -1,  1,  1,  3,  0,  6,  0, -3, -1,  0,  1,  0,  1, -2, -2,  0, -5},
				{-2, -1, -1, -3, -4, -4, -3, -3, -3,  1,  2, -1,  0,  8, -4, -1, -1,  1,  3,  1, -2, -3, -1, -5},
				{-2, -2, -2, -1, -4,  0,  0, -2, -1, -1, -3,  0, -3, -4, 10, -2,  0, -4, -3, -3, -1,  0, -1, -5},
				{ 1, -1,  0, -1, -3,  0,  0,  1, -1, -2, -2,  0, -1, -1, -2,  4,  2, -2, -1, -1,  0,  0,  0, -5},
				{ 0, -2,  0, -1, -1,  0, -1, -2, -2, -1,  0,  0,  0, -1,  0,  2,  5, -2, -2,  1, -1, -1,  0, -5},
				{-2,  0, -2, -3, -5, -1, -1, -1, -4, -1,  0,  0,  1,  1, -4, -2, -2, 16,  3, -2, -3, -1, -1, -5},
				{-1,  0, -2, -2, -5,  0, -1, -2,  0,  0,  0, -1,  0,  3, -3, -1, -2,  3,  8,  0, -2, -1, -1, -5},
				{ 0, -1, -2, -2, -2, -3, -2, -3, -4,  4,  2, -2,  1,  1, -3, -1,  1, -2,  0,  5, -2, -2,  0, -5},
				{-1, -1,  4,  5, -2,  0,  0,  0,  0, -2, -2,  0, -2, -2, -1,  0, -1, -3, -2, -2,  5,  0, -1, -5},
				{-1,  0,  0,  1, -2,  4,  5, -2, -1, -3, -2,  1, -2, -3,  0,  0, -1, -1, -1, -2,  0,  4,  0, -5},
				{ 0, -1,  0, -1, -2, -1, -1, -1, -1,  0,  0,  0,  0, -1, -1,  0,  0, -1, -1,  0, -1,  0, -1, -5},
				{-5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5,  1},
		};

	public static final int[][] b40Matrix =
		{
				{ 5, -2, -1, -1, -2,  0, -1,  1, -2, -1, -2, -1, -1, -3, -2,  1,  0, -3, -2,  0, -1, -1,  0, -6},
				{-2,  9,  0, -1, -3,  2, -1, -3,  0, -3, -2,  3, -1, -2, -3, -1, -2, -2, -1, -2, -1,  0, -1, -6},
				{-1,  0,  8,  2, -2,  1, -1,  0,  1, -2, -3,  0, -2, -3, -2,  1,  0, -4, -2, -3,  4,  0, -1, -6},
				{-1, -1,  2,  9, -2, -1,  2, -2,  0, -4, -3,  0, -3, -4, -2,  0, -1, -5, -3, -3,  6,  1, -1, -6},
				{-2, -3, -2, -2, 16, -4, -2, -3, -4, -4, -2, -3, -3, -2, -5, -1, -1, -6, -4, -2, -2, -3, -2, -6},
				{ 0,  2,  1, -1, -4,  8,  2, -2,  0, -3, -2,  1, -1, -4, -2,  1, -1, -1, -1, -3,  0,  4, -1, -6},
				{-1, -1, -1,  2, -2,  2,  7, -3,  0, -4, -2,  1, -2, -3,  0,  0, -1, -2, -2, -3,  1,  5, -1, -6},
				{ 1, -3,  0, -2, -3, -2, -3,  8, -2, -4, -4, -2, -2, -3, -1,  0, -2, -2, -3, -4, -1, -2, -1, -6},
				{-2,  0,  1,  0, -4,  0,  0, -2, 13, -3, -2, -1,  1, -2, -2, -1, -2, -5,  2, -4,  0,  0, -1, -6},
				{-1, -3, -2, -4, -4, -3, -4, -4, -3,  6,  2, -3,  1,  1, -2, -2, -1, -3,  0,  4, -3, -4, -1, -6},
				{-2, -2, -3, -3, -2, -2, -2, -4, -2,  2,  6, -2,  3,  2, -4, -3, -1, -1,  0,  2, -3, -2, -1, -6},
				{-1,  3,  0,  0, -3,  1,  1, -2, -1, -3, -2,  6, -1, -3, -1,  0,  0, -2, -1, -2,  0,  1, -1, -6},
				{-1, -1, -2, -3, -3, -1, -2, -2,  1,  1,  3, -1,  7,  0, -2, -2, -1, -2,  1,  1, -3, -2,  0, -6},
				{-3, -2, -3, -4, -2, -4, -3, -3, -2,  1,  2, -3,  0,  9, -4, -2, -1,  1,  4,  0, -3, -4, -1, -6},
				{-2, -3, -2, -2, -5, -2,  0, -1, -2, -2, -4, -1, -2, -4, 11, -1,  0, -4, -3, -3, -2, -1, -2, -6},
				{ 1, -1,  1,  0, -1,  1,  0,  0, -1, -2, -3,  0, -2, -2, -1,  5,  2, -5, -2, -1,  0,  0,  0, -6},
				{ 0, -2,  0, -1, -1, -1, -1, -2, -2, -1, -1,  0, -1, -1,  0,  2,  6, -4, -1,  1,  0, -1,  0, -6},
				{-3, -2, -4, -5, -6, -1, -2, -2, -5, -3, -1, -2, -2,  1, -4, -5, -4, 19,  3, -3, -4, -2, -2, -6},
				{-2, -1, -2, -3, -4, -1, -2, -3,  2,  0,  0, -1,  1,  4, -3, -2, -1,  3,  9, -1, -3, -2, -1, -6},
				{ 0, -2, -3, -3, -2, -3, -3, -4, -4,  4,  2, -2,  1,  0, -3, -1,  1, -3, -1,  5, -3, -3, -1, -6},
				{-1, -1,  4,  6, -2,  0,  1, -1,  0, -3, -3,  0, -3, -3, -2,  0,  0, -4, -3, -3,  5,  2, -1, -6},
				{-1,  0,  0,  1, -3,  4,  5, -2,  0, -4, -2,  1, -2, -4, -1,  0, -1, -2, -2, -3,  2,  5, -1, -6},
				{ 0, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1, -1,  0, -1, -2,  0,  0, -2, -1, -1, -1, -1, -1, -6},
				{-6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6,  1},
		};

	public static final int[][] b45Matrix =
		{
				{ 5, -2, -1, -2, -1, -1, -1,  0, -2, -1, -1, -1, -1, -2, -1,  1,  0, -2, -2,  0, -1, -1,  0, -5},
				{-2,  7,  0, -1, -3,  1,  0, -2,  0, -3, -2,  3, -1, -2, -2, -1, -1, -2, -1, -2, -1,  0, -1, -5},
				{-1,  0,  6,  2, -2,  0,  0,  0,  1, -2, -3,  0, -2, -2, -2,  1,  0, -4, -2, -3,  4,  0, -1, -5},
				{-2, -1,  2,  7, -3,  0,  2, -1,  0, -4, -3,  0, -3, -4, -1,  0, -1, -4, -2, -3,  5,  1, -1, -5},
				{-1, -3, -2, -3, 12, -3, -3, -3, -3, -3, -2, -3, -2, -2, -4, -1, -1, -5, -3, -1, -2, -3, -2, -5},
				{-1,  1,  0,  0, -3,  6,  2, -2,  1, -2, -2,  1,  0, -4, -1,  0, -1, -2, -1, -3,  0,  4, -1, -5},
				{-1,  0,  0,  2, -3,  2,  6, -2,  0, -3, -2,  1, -2, -3,  0,  0, -1, -3, -2, -3,  1,  4, -1, -5},
				{ 0, -2,  0, -1, -3, -2, -2,  7, -2, -4, -3, -2, -2, -3, -2,  0, -2, -2, -3, -3, -1, -2, -1, -5},
				{-2,  0,  1,  0, -3,  1,  0, -2, 10, -3, -2, -1,  0, -2, -2, -1, -2, -3,  2, -3,  0,  0, -1, -5},
				{-1, -3, -2, -4, -3, -2, -3, -4, -3,  5,  2, -3,  2,  0, -2, -2, -1, -2,  0,  3, -3, -3, -1, -5},
				{-1, -2, -3, -3, -2, -2, -2, -3, -2,  2,  5, -3,  2,  1, -3, -3, -1, -2,  0,  1, -3, -2, -1, -5},
				{-1,  3,  0,  0, -3,  1,  1, -2, -1, -3, -3,  5, -1, -3, -1, -1, -1, -2, -1, -2,  0,  1, -1, -5},
				{-1, -1, -2, -3, -2,  0, -2, -2,  0,  2,  2, -1,  6,  0, -2, -2, -1, -2,  0,  1, -2, -1, -1, -5},
				{-2, -2, -2, -4, -2, -4, -3, -3, -2,  0,  1, -3,  0,  8, -3, -2, -1,  1,  3,  0, -3, -3, -1, -5},
				{-1, -2, -2, -1, -4, -1,  0, -2, -2, -2, -3, -1, -2, -3,  9, -1, -1, -3, -3, -3, -2, -1, -1, -5},
				{ 1, -1,  1,  0, -1,  0,  0,  0, -1, -2, -3, -1, -2, -2, -1,  4,  2, -4, -2, -1,  0,  0,  0, -5},
				{ 0, -1,  0, -1, -1, -1, -1, -2, -2, -1, -1, -1, -1, -1, -1,  2,  5, -3, -1,  0,  0, -1,  0, -5},
				{-2, -2, -4, -4, -5, -2, -3, -2, -3, -2, -2, -2, -2,  1, -3, -4, -3, 15,  3, -3, -4, -2, -2, -5},
				{-2, -1, -2, -2, -3, -1, -2, -3,  2,  0,  0, -1,  0,  3, -3, -2, -1,  3,  8, -1, -2, -2, -1, -5},
				{ 0, -2, -3, -3, -1, -3, -3, -3, -3,  3,  1, -2,  1,  0, -3, -1,  0, -3, -1,  5, -3, -3, -1, -5},
				{-1, -1,  4,  5, -2,  0,  1, -1,  0, -3, -3,  0, -2, -3, -2,  0,  0, -4, -2, -3,  4,  2, -1, -5},
				{-1,  0,  0,  1, -3,  4,  4, -2,  0, -3, -2,  1, -1, -3, -1,  0, -1, -2, -2, -3,  2,  4, -1, -5},
				{ 0, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  0,  0, -2, -1, -1, -1, -1, -1, -5},
				{-5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5,  1},
		};

	public static final int[][] b50Matrix =
		{
				{ 5, -2, -1, -2, -1, -1, -1,  0, -2, -1, -2, -1, -1, -3, -1,  1,  0, -3, -2,  0, -2, -1, -1, -5},
				{-2,  7, -1, -2, -4,  1,  0, -3,  0, -4, -3,  3, -2, -3, -3, -1, -1, -3, -1, -3, -1,  0, -1, -5},
				{-1, -1,  7,  2, -2,  0,  0,  0,  1, -3, -4,  0, -2, -4, -2,  1,  0, -4, -2, -3,  4,  0, -1, -5},
				{-2, -2,  2,  8, -4,  0,  2, -1, -1, -4, -4, -1, -4, -5, -1,  0, -1, -5, -3, -4,  5,  1, -1, -5},
				{-1, -4, -2, -4, 13, -3, -3, -3, -3, -2, -2, -3, -2, -2, -4, -1, -1, -5, -3, -1, -3, -3, -2, -5},
				{-1,  1,  0,  0, -3,  7,  2, -2,  1, -3, -2,  2,  0, -4, -1,  0, -1, -1, -1, -3,  0,  4, -1, -5},
				{-1,  0,  0,  2, -3,  2,  6, -3,  0, -4, -3,  1, -2, -3, -1, -1, -1, -3, -2, -3,  1,  5, -1, -5},
				{ 0, -3,  0, -1, -3, -2, -3,  8, -2, -4, -4, -2, -3, -4, -2,  0, -2, -3, -3, -4, -1, -2, -2, -5},
				{-2,  0,  1, -1, -3,  1,  0, -2, 10, -4, -3,  0, -1, -1, -2, -1, -2, -3,  2, -4,  0,  0, -1, -5},
				{-1, -4, -3, -4, -2, -3, -4, -4, -4,  5,  2, -3,  2,  0, -3, -3, -1, -3, -1,  4, -4, -3, -1, -5},
				{-2, -3, -4, -4, -2, -2, -3, -4, -3,  2,  5, -3,  3,  1, -4, -3, -1, -2, -1,  1, -4, -3, -1, -5},
				{-1,  3,  0, -1, -3,  2,  1, -2,  0, -3, -3,  6, -2, -4, -1,  0, -1, -3, -2, -3,  0,  1, -1, -5},
				{-1, -2, -2, -4, -2,  0, -2, -3, -1,  2,  3, -2,  7,  0, -3, -2, -1, -1,  0,  1, -3, -1, -1, -5},
				{-3, -3, -4, -5, -2, -4, -3, -4, -1,  0,  1, -4,  0,  8, -4, -3, -2,  1,  4, -1, -4, -4, -2, -5},
				{-1, -3, -2, -1, -4, -1, -1, -2, -2, -3, -4, -1, -3, -4, 10, -1, -1, -4, -3, -3, -2, -1, -2, -5},
				{ 1, -1,  1,  0, -1,  0, -1,  0, -1, -3, -3,  0, -2, -3, -1,  5,  2, -4, -2, -2,  0,  0, -1, -5},
				{ 0, -1,  0, -1, -1, -1, -1, -2, -2, -1, -1, -1, -1, -2, -1,  2,  5, -3, -2,  0,  0, -1,  0, -5},
				{-3, -3, -4, -5, -5, -1, -3, -3, -3, -3, -2, -3, -1,  1, -4, -4, -3, 15,  2, -3, -5, -2, -3, -5},
				{-2, -1, -2, -3, -3, -1, -2, -3,  2, -1, -1, -2,  0,  4, -3, -2, -2,  2,  8, -1, -3, -2, -1, -5},
				{ 0, -3, -3, -4, -1, -3, -3, -4, -4,  4,  1, -3,  1, -1, -3, -2,  0, -3, -1,  5, -4, -3, -1, -5},
				{-2, -1,  4,  5, -3,  0,  1, -1,  0, -4, -4,  0, -3, -4, -2,  0,  0, -5, -3, -4,  5,  2, -1, -5},
				{-1,  0,  0,  1, -3,  4,  5, -2,  0, -3, -3,  1, -1, -4, -1,  0, -1, -2, -2, -3,  2,  5, -1, -5},
				{-1, -1, -1, -1, -2, -1, -1, -2, -1, -1, -1, -1, -1, -2, -2, -1,  0, -3, -1, -1, -1, -1, -1, -5},
				{-5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5,  1},
		};

	public static final int[][] b55Matrix =
		{
				{ 5, -2, -2, -2,  0, -1, -1,  0, -2, -2, -2, -1, -1, -3, -1,  2,  0, -4, -2,  0, -2, -1, -1, -5},
				{-2,  8, -1, -2, -4,  1,  0, -3,  0, -4, -3,  3, -2, -3, -3, -1, -1, -3, -2, -3, -1,  0, -1, -5},
				{-2, -1,  8,  2, -3,  0,  0,  0,  1, -4, -4,  0, -3, -4, -2,  1,  0, -5, -2, -4,  4,  0, -1, -5},
				{-2, -2,  2,  8, -4,  0,  2, -2, -1, -4, -5, -1, -4, -5, -2,  0, -1, -5, -3, -4,  5,  1, -2, -5},
				{ 0, -4, -3, -4, 13, -4, -4, -3, -4, -2, -2, -4, -2, -3, -3, -1, -1, -4, -3, -1, -4, -4, -2, -5},
				{-1,  1,  0,  0, -4,  7,  2, -2,  1, -4, -3,  2,  0, -4, -1,  0, -1, -2, -1, -3,  0,  4, -1, -5},
				{-1,  0,  0,  2, -4,  2,  7, -3, -1, -4, -4,  1, -3, -4, -1,  0, -1, -3, -2, -3,  1,  5, -1, -5},
				{ 0, -3,  0, -2, -3, -2, -3,  8, -2, -5, -5, -2, -3, -4, -3,  0, -2, -3, -4, -4, -1, -3, -2, -5},
				{-2,  0,  1, -1, -4,  1, -1, -2, 11, -4, -3,  0, -2, -1, -3, -1, -2, -3,  2, -4,  0,  0, -1, -5},
				{-2, -4, -4, -4, -2, -4, -4, -5, -4,  6,  2, -4,  2,  0, -3, -3, -1, -3, -1,  4, -4, -4, -1, -5},
				{-2, -3, -4, -5, -2, -3, -4, -5, -3,  2,  6, -3,  3,  1, -4, -3, -2, -3, -1,  1, -4, -3, -1, -5},
				{-1,  3,  0, -1, -4,  2,  1, -2,  0, -4, -3,  6, -2, -4, -1,  0, -1, -4, -2, -3,  0,  1, -1, -5},
				{-1, -2, -3, -4, -2,  0, -3, -3, -2,  2,  3, -2,  8,  0, -3, -2, -1, -2, -1,  1, -3, -2, -1, -5},
				{-3, -3, -4, -5, -3, -4, -4, -4, -1,  0,  1, -4,  0,  9, -5, -3, -3,  2,  4, -1, -5, -4, -2, -5},
				{-1, -3, -2, -2, -3, -1, -1, -3, -3, -3, -4, -1, -3, -5, 10, -1, -1, -5, -4, -3, -2, -1, -2, -5},
				{ 2, -1,  1,  0, -1,  0,  0,  0, -1, -3, -3,  0, -2, -3, -1,  5,  2, -4, -2, -2,  0,  0, -1, -5},
				{ 0, -1,  0, -1, -1, -1, -1, -2, -2, -1, -2, -1, -1, -3, -1,  2,  6, -3, -2,  0, -1, -1, -1, -5},
				{-4, -3, -5, -5, -4, -2, -3, -3, -3, -3, -3, -4, -2,  2, -5, -4, -3, 15,  3, -4, -5, -3, -3, -5},
				{-2, -2, -2, -3, -3, -1, -2, -4,  2, -1, -1, -2, -1,  4, -4, -2, -2,  3,  9, -2, -3, -2, -1, -5},
				{ 0, -3, -4, -4, -1, -3, -3, -4, -4,  4,  1, -3,  1, -1, -3, -2,  0, -4, -2,  5, -4, -3, -1, -5},
				{-2, -1,  4,  5, -4,  0,  1, -1,  0, -4, -4,  0, -3, -5, -2,  0, -1, -5, -3, -4,  5,  2, -1, -5},
				{-1,  0,  0,  1, -4,  4,  5, -3,  0, -4, -3,  1, -2, -4, -1,  0, -1, -3, -2, -3,  2,  5, -1, -5},
				{-1, -1, -1, -2, -2, -1, -1, -2, -1, -1, -1, -1, -1, -2, -2, -1, -1, -3, -1, -1, -1, -1, -1, -5},
				{-5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5,  1},
		};

	public static final int[][] b60Matrix =
		{
				{ 4, -1, -1, -2,  0, -1, -1,  0, -2, -1, -1, -1, -1, -2, -1,  1,  0, -3, -2,  0, -2, -1,  0, -4},
				{-1,  5,  0, -1, -3,  1,  0, -2,  0, -3, -2,  2, -1, -3, -2, -1, -1, -3, -2, -2, -1,  0, -1, -4},
				{-1,  0,  6,  1, -2,  0,  0,  0,  1, -3, -3,  0, -2, -3, -2,  1,  0, -4, -2, -3,  3,  0, -1, -4},
				{-2, -1,  1,  6, -3,  0,  2, -1, -1, -3, -3, -1, -3, -3, -1,  0, -1, -4, -3, -3,  4,  1, -1, -4},
				{ 0, -3, -2, -3,  9, -3, -3, -2, -3, -1, -1, -3, -1, -2, -3, -1, -1, -2, -2, -1, -3, -3, -2, -4},
				{-1,  1,  0,  0, -3,  5,  2, -2,  1, -3, -2,  1,  0, -3, -1,  0, -1, -2, -1, -2,  0,  3, -1, -4},
				{-1,  0,  0,  2, -3,  2,  5, -2,  0, -3, -3,  1, -2, -3, -1,  0, -1, -3, -2, -2,  1,  4, -1, -4},
				{ 0, -2,  0, -1, -2, -2, -2,  6, -2, -3, -4, -1, -2, -3, -2,  0, -2, -2, -3, -3, -1, -2, -1, -4},
				{-2,  0,  1, -1, -3,  1,  0, -2,  7, -3, -3, -1, -1, -1, -2, -1, -2, -2,  2, -3,  0,  0, -1, -4},
				{-1, -3, -3, -3, -1, -3, -3, -3, -3,  4,  2, -3,  1,  0, -3, -2, -1, -2, -1,  3, -3, -3, -1, -4},
				{-1, -2, -3, -3, -1, -2, -3, -4, -3,  2,  4, -2,  2,  0, -3, -2, -1, -2, -1,  1, -3, -2, -1, -4},
				{-1,  2,  0, -1, -3,  1,  1, -1, -1, -3, -2,  4, -1, -3, -1,  0, -1, -3, -2, -2,  0,  1, -1, -4},
				{-1, -1, -2, -3, -1,  0, -2, -2, -1,  1,  2, -1,  5,  0, -2, -1, -1, -1, -1,  1, -3, -1, -1, -4},
				{-2, -3, -3, -3, -2, -3, -3, -3, -1,  0,  0, -3,  0,  6, -4, -2, -2,  1,  3, -1, -3, -3, -1, -4},
				{-1, -2, -2, -1, -3, -1, -1, -2, -2, -3, -3, -1, -2, -4,  7, -1, -1, -4, -3, -2, -2, -1, -2, -4},
				{ 1, -1,  1,  0, -1,  0,  0,  0, -1, -2, -2,  0, -1, -2, -1,  4,  1, -3, -2, -2,  0,  0,  0, -4},
				{ 0, -1,  0, -1, -1, -1, -1, -2, -2, -1, -1, -1, -1, -2, -1,  1,  4, -2, -2,  0,  0, -1,  0, -4},
				{-3, -3, -4, -4, -2, -2, -3, -2, -2, -2, -2, -3, -1,  1, -4, -3, -2, 10,  2, -3, -4, -2, -2, -4},
				{-2, -2, -2, -3, -2, -1, -2, -3,  2, -1, -1, -2, -1,  3, -3, -2, -2,  2,  6, -1, -2, -2, -1, -4},
				{ 0, -2, -3, -3, -1, -2, -2, -3, -3,  3,  1, -2,  1, -1, -2, -2,  0, -3, -1,  4, -3, -2, -1, -4},
				{-2, -1,  3,  4, -3,  0,  1, -1,  0, -3, -3,  0, -3, -3, -2,  0,  0, -4, -2, -3,  4,  1, -1, -4},
				{-1,  0,  0,  1, -3,  3,  4, -2,  0, -3, -2,  1, -1, -3, -1,  0, -1, -2, -2, -2,  1,  3, -1, -4},
				{ 0, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -2,  0,  0, -2, -1, -1, -1, -1, -1, -4},
				{-4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4,  1},
		};

	public static final int[][] b62Matrix =
		{
				{ 4, -1, -2, -2,  0, -1, -1,  0, -2, -1, -1, -1, -1, -2, -1,  1,  0, -3, -2,  0, -2, -1,  0, -4},
				{-1,  5,  0, -2, -3,  1,  0, -2,  0, -3, -2,  2, -1, -3, -2, -1, -1, -3, -2, -3, -1,  0, -1, -4},
				{-2,  0,  6,  1, -3,  0,  0,  0,  1, -3, -3,  0, -2, -3, -2,  1,  0, -4, -2, -3,  3,  0, -1, -4},
				{-2, -2,  1,  6, -3,  0,  2, -1, -1, -3, -4, -1, -3, -3, -1,  0, -1, -4, -3, -3,  4,  1, -1, -4},
				{ 0, -3, -3, -3,  9, -3, -4, -3, -3, -1, -1, -3, -1, -2, -3, -1, -1, -2, -2, -1, -3, -3, -2, -4},
				{-1,  1,  0,  0, -3,  5,  2, -2,  0, -3, -2,  1,  0, -3, -1,  0, -1, -2, -1, -2,  0,  3, -1, -4},
				{-1,  0,  0,  2, -4,  2,  5, -2,  0, -3, -3,  1, -2, -3, -1,  0, -1, -3, -2, -2,  1,  4, -1, -4},
				{ 0, -2,  0, -1, -3, -2, -2,  6, -2, -4, -4, -2, -3, -3, -2,  0, -2, -2, -3, -3, -1, -2, -1, -4},
				{-2,  0,  1, -1, -3,  0,  0, -2,  8, -3, -3, -1, -2, -1, -2, -1, -2, -2,  2, -3,  0,  0, -1, -4},
				{-1, -3, -3, -3, -1, -3, -3, -4, -3,  4,  2, -3,  1,  0, -3, -2, -1, -3, -1,  3, -3, -3, -1, -4},
				{-1, -2, -3, -4, -1, -2, -3, -4, -3,  2,  4, -2,  2,  0, -3, -2, -1, -2, -1,  1, -4, -3, -1, -4},
				{-1,  2,  0, -1, -3,  1,  1, -2, -1, -3, -2,  5, -1, -3, -1,  0, -1, -3, -2, -2,  0,  1, -1, -4},
				{-1, -1, -2, -3, -1,  0, -2, -3, -2,  1,  2, -1,  5,  0, -2, -1, -1, -1, -1,  1, -3, -1, -1, -4},
				{-2, -3, -3, -3, -2, -3, -3, -3, -1,  0,  0, -3,  0,  6, -4, -2, -2,  1,  3, -1, -3, -3, -1, -4},
				{-1, -2, -2, -1, -3, -1, -1, -2, -2, -3, -3, -1, -2, -4,  7, -1, -1, -4, -3, -2, -2, -1, -2, -4},
				{ 1, -1,  1,  0, -1,  0,  0,  0, -1, -2, -2,  0, -1, -2, -1,  4,  1, -3, -2, -2,  0,  0,  0, -4},
				{ 0, -1,  0, -1, -1, -1, -1, -2, -2, -1, -1, -1, -1, -2, -1,  1,  5, -2, -2,  0, -1, -1,  0, -4},
				{-3, -3, -4, -4, -2, -2, -3, -2, -2, -3, -2, -3, -1,  1, -4, -3, -2, 11,  2, -3, -4, -3, -2, -4},
				{-2, -2, -2, -3, -2, -1, -2, -3,  2, -1, -1, -2, -1,  3, -3, -2, -2,  2,  7, -1, -3, -2, -1, -4},
				{ 0, -3, -3, -3, -1, -2, -2, -3, -3,  3,  1, -2,  1, -1, -2, -2,  0, -3, -1,  4, -3, -2, -1, -4},
				{-2, -1,  3,  4, -3,  0,  1, -1,  0, -3, -4,  0, -3, -3, -2,  0, -1, -4, -3, -3,  4,  1, -1, -4},
				{-1,  0,  0,  1, -3,  3,  4, -2,  0, -3, -3,  1, -1, -3, -1,  0, -1, -3, -2, -2,  1,  4, -1, -4},
				{ 0, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -2,  0,  0, -2, -1, -1, -1, -1, -1, -4},
				{-4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4, -4,  1},
		};

	public static final int[][] b65Matrix =
		{
				{ 4, -1, -2, -2,  0, -1, -1,  0, -2, -1, -2, -1, -1, -2, -1,  1,  0, -3, -2,  0, -2, -1, -1, -5},
				{-1,  6,  0, -2, -4,  1,  0, -2,  0, -3, -2,  2, -2, -3, -2, -1, -1, -3, -2, -3, -1,  0, -1, -5},
				{-2,  0,  6,  1, -3,  0,  0, -1,  1, -3, -4,  0, -2, -3, -2,  1,  0, -4, -2, -3,  3,  0, -1, -5},
				{-2, -2,  1,  6, -4,  0,  2, -1, -1, -3, -4, -1, -3, -4, -2,  0, -1, -5, -3, -3,  4,  1, -1, -5},
				{ 0, -4, -3, -4,  9, -3, -4, -3, -3, -1, -1, -3, -2, -2, -3, -1, -1, -2, -2, -1, -3, -4, -2, -5},
				{-1,  1,  0,  0, -3,  6,  2, -2,  1, -3, -2,  1,  0, -3, -1,  0, -1, -2, -2, -2,  0,  3, -1, -5},
				{-1,  0,  0,  2, -4,  2,  5, -2,  0, -3, -3,  1, -2, -3, -1,  0, -1, -3, -2, -3,  1,  4, -1, -5},
				{ 0, -2, -1, -1, -3, -2, -2,  6, -2, -4, -4, -2, -3, -3, -2,  0, -2, -3, -3, -3, -1, -2, -2, -5},
				{-2,  0,  1, -1, -3,  1,  0, -2,  8, -3, -3, -1, -2, -1, -2, -1, -2, -2,  2, -3,  0,  0, -1, -5},
				{-1, -3, -3, -3, -1, -3, -3, -4, -3,  4,  2, -3,  1,  0, -3, -2, -1, -2, -1,  3, -3, -3, -1, -5},
				{-2, -2, -4, -4, -1, -2, -3, -4, -3,  2,  4, -3,  2,  0, -3, -3, -1, -2, -1,  1, -4, -3, -1, -5},
				{-1,  2,  0, -1, -3,  1,  1, -2, -1, -3, -3,  5, -2, -3, -1,  0, -1, -3, -2, -2,  0,  1, -1, -5},
				{-1, -2, -2, -3, -2,  0, -2, -3, -2,  1,  2, -2,  6,  0, -3, -2, -1, -2, -1,  1, -3, -2, -1, -5},
				{-2, -3, -3, -4, -2, -3, -3, -3, -1,  0,  0, -3,  0,  6, -4, -2, -2,  1,  3, -1, -3, -3, -2, -5},
				{-1, -2, -2, -2, -3, -1, -1, -2, -2, -3, -3, -1, -3, -4,  8, -1, -1, -4, -3, -2, -2, -1, -2, -5},
				{ 1, -1,  1,  0, -1,  0,  0,  0, -1, -2, -3,  0, -2, -2, -1,  4,  1, -3, -2, -2,  0,  0, -1, -5},
				{ 0, -1,  0, -1, -1, -1, -1, -2, -2, -1, -1, -1, -1, -2, -1,  1,  5, -3, -2,  0, -1, -1, -1, -5},
				{-3, -3, -4, -5, -2, -2, -3, -3, -2, -2, -2, -3, -2,  1, -4, -3, -3, 10,  2, -3, -4, -3, -2, -5},
				{-2, -2, -2, -3, -2, -2, -2, -3,  2, -1, -1, -2, -1,  3, -3, -2, -2,  2,  7, -1, -3, -2, -1, -5},
				{ 0, -3, -3, -3, -1, -2, -3, -3, -3,  3,  1, -2,  1, -1, -2, -2,  0, -3, -1,  4, -3, -2, -1, -5},
				{-2, -1,  3,  4, -3,  0,  1, -1,  0, -3, -4,  0, -3, -3, -2,  0, -1, -4, -3, -3,  4,  1, -1, -5},
				{-1,  0,  0,  1, -4,  3,  4, -2,  0, -3, -3,  1, -2, -3, -1,  0, -1, -3, -2, -2,  1,  4, -1, -5},
				{-1, -1, -1, -1, -2, -1, -1, -2, -1, -1, -1, -1, -1, -2, -2, -1, -1, -2, -1, -1, -1, -1, -1, -5},
				{-5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5,  1},
		};

	public static final int[][] b70Matrix =
		{
				{ 4, -2, -2, -2, -1, -1, -1,  0, -2, -2, -2, -1, -1, -2, -1,  1,  0, -3, -2,  0, -2, -1, -1, -5},
				{-2,  6, -1, -2, -4,  1,  0, -3,  0, -3, -3,  2, -2, -3, -2, -1, -1, -3, -2, -3, -1,  0, -1, -5},
				{-2, -1,  6,  1, -3,  0,  0, -1,  0, -4, -4,  0, -2, -3, -2,  0,  0, -4, -2, -3,  3,  0, -1, -5},
				{-2, -2,  1,  6, -4, -1,  1, -2, -1, -4, -4, -1, -3, -4, -2,  0, -1, -5, -4, -4,  4,  1, -2, -5},
				{-1, -4, -3, -4,  9, -3, -4, -3, -4, -1, -2, -4, -2, -2, -3, -1, -1, -3, -3, -1, -4, -4, -2, -5},
				{-1,  1,  0, -1, -3,  6,  2, -2,  1, -3, -2,  1,  0, -3, -2,  0, -1, -2, -2, -2,  0,  3, -1, -5},
				{-1,  0,  0,  1, -4,  2,  5, -2,  0, -4, -3,  1, -2, -4, -1,  0, -1, -4, -3, -3,  1,  4, -1, -5},
				{ 0, -3, -1, -2, -3, -2, -2,  6, -2, -4, -4, -2, -3, -4, -3, -1, -2, -3, -4, -4, -1, -2, -2, -5},
				{-2,  0,  0, -1, -4,  1,  0, -2,  8, -4, -3, -1, -2, -1, -2, -1, -2, -2,  2, -3, -1,  0, -1, -5},
				{-2, -3, -4, -4, -1, -3, -4, -4, -4,  4,  2, -3,  1,  0, -3, -3, -1, -3, -1,  3, -4, -3, -1, -5},
				{-2, -3, -4, -4, -2, -2, -3, -4, -3,  2,  4, -3,  2,  0, -3, -3, -2, -2, -1,  1, -4, -3, -1, -5},
				{-1,  2,  0, -1, -4,  1,  1, -2, -1, -3, -3,  5, -2, -3, -1,  0, -1, -3, -2, -3, -1,  1, -1, -5},
				{-1, -2, -2, -3, -2,  0, -2, -3, -2,  1,  2, -2,  6,  0, -3, -2, -1, -2, -1,  1, -3, -2, -1, -5},
				{-2, -3, -3, -4, -2, -3, -4, -4, -1,  0,  0, -3,  0,  6, -4, -3, -2,  1,  3, -1, -4, -4, -2, -5},
				{-1, -2, -2, -2, -3, -2, -1, -3, -2, -3, -3, -1, -3, -4,  8, -1, -1, -4, -3, -3, -2, -1, -2, -5},
				{ 1, -1,  0,  0, -1,  0,  0, -1, -1, -3, -3,  0, -2, -3, -1,  4,  1, -3, -2, -2,  0,  0, -1, -5},
				{ 0, -1,  0, -1, -1, -1, -1, -2, -2, -1, -2, -1, -1, -2, -1,  1,  5, -3, -2,  0, -1, -1, -1, -5},
				{-3, -3, -4, -5, -3, -2, -4, -3, -2, -3, -2, -3, -2,  1, -4, -3, -3, 11,  2, -3, -4, -3, -3, -5},
				{-2, -2, -2, -4, -3, -2, -3, -4,  2, -1, -1, -2, -1,  3, -3, -2, -2,  2,  7, -2, -3, -2, -2, -5},
				{ 0, -3, -3, -4, -1, -2, -3, -4, -3,  3,  1, -3,  1, -1, -3, -2,  0, -3, -2,  4, -3, -3, -1, -5},
				{-2, -1,  3,  4, -4,  0,  1, -1, -1, -4, -4, -1, -3, -4, -2,  0, -1, -4, -3, -3,  4,  0, -1, -5},
				{-1,  0,  0,  1, -4,  3,  4, -2,  0, -3, -3,  1, -2, -4, -1,  0, -1, -3, -2, -3,  0,  4, -1, -5},
				{-1, -1, -1, -2, -2, -1, -1, -2, -1, -1, -1, -1, -1, -2, -2, -1, -1, -3, -2, -1, -1, -1, -1, -5},
				{-5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5,  1},
		};

	public static final int[][] b75Matrix =
		{
				{ 4, -2, -2, -2, -1, -1, -1,  0, -2, -2, -2, -1, -1, -3, -1,  1,  0, -3, -2,  0, -2, -1, -1, -5},
				{-2,  6, -1, -2, -4,  1,  0, -3,  0, -3, -3,  2, -2, -3, -2, -1, -1, -3, -2, -3, -1,  0, -1, -5},
				{-2, -1,  6,  1, -3,  0, -1, -1,  0, -4, -4,  0, -3, -4, -3,  0,  0, -4, -3, -3,  3,  0, -1, -5},
				{-2, -2,  1,  6, -4, -1,  1, -2, -1, -4, -4, -1, -4, -4, -2, -1, -1, -5, -4, -4,  4,  1, -2, -5},
				{-1, -4, -3, -4,  9, -3, -5, -3, -4, -1, -2, -4, -2, -2, -4, -1, -1, -3, -3, -1, -4, -4, -2, -5},
				{-1,  1,  0, -1, -3,  6,  2, -2,  1, -3, -3,  1,  0, -4, -2,  0, -1, -2, -2, -2,  0,  3, -1, -5},
				{-1,  0, -1,  1, -5,  2,  5, -3,  0, -4, -4,  1, -2, -4, -1,  0, -1, -4, -3, -3,  1,  4, -1, -5},
				{ 0, -3, -1, -2, -3, -2, -3,  6, -2, -5, -4, -2, -3, -4, -3, -1, -2, -3, -4, -4, -1, -2, -2, -5},
				{-2,  0,  0, -1, -4,  1,  0, -2,  8, -4, -3, -1, -2, -2, -2, -1, -2, -2,  2, -4, -1,  0, -1, -5},
				{-2, -3, -4, -4, -1, -3, -4, -5, -4,  4,  1, -3,  1,  0, -3, -3, -1, -3, -2,  3, -4, -4, -2, -5},
				{-2, -3, -4, -4, -2, -3, -4, -4, -3,  1,  4, -3,  2,  0, -3, -3, -2, -2, -1,  1, -4, -3, -1, -5},
				{-1,  2,  0, -1, -4,  1,  1, -2, -1, -3, -3,  5, -2, -4, -1,  0, -1, -4, -2, -3, -1,  1, -1, -5},
				{-1, -2, -3, -4, -2,  0, -2, -3, -2,  1,  2, -2,  6,  0, -3, -2, -1, -2, -2,  1, -3, -2, -1, -5},
				{-3, -3, -4, -4, -2, -4, -4, -4, -2,  0,  0, -4,  0,  6, -4, -3, -2,  1,  3, -1, -4, -4, -2, -5},
				{-1, -2, -3, -2, -4, -2, -1, -3, -2, -3, -3, -1, -3, -4,  8, -1, -1, -5, -4, -3, -2, -2, -2, -5},
				{ 1, -1,  0, -1, -1,  0,  0, -1, -1, -3, -3,  0, -2, -3, -1,  5,  1, -3, -2, -2,  0,  0, -1, -5},
				{ 0, -1,  0, -1, -1, -1, -1, -2, -2, -1, -2, -1, -1, -2, -1,  1,  5, -3, -2,  0, -1, -1, -1, -5},
				{-3, -3, -4, -5, -3, -2, -4, -3, -2, -3, -2, -4, -2,  1, -5, -3, -3, 11,  2, -3, -5, -3, -3, -5},
				{-2, -2, -3, -4, -3, -2, -3, -4,  2, -2, -1, -2, -2,  3, -4, -2, -2,  2,  7, -2, -3, -3, -2, -5},
				{ 0, -3, -3, -4, -1, -2, -3, -4, -4,  3,  1, -3,  1, -1, -3, -2,  0, -3, -2,  4, -4, -3, -1, -5},
				{-2, -1,  3,  4, -4,  0,  1, -1, -1, -4, -4, -1, -3, -4, -2,  0, -1, -5, -3, -4,  4,  0, -2, -5},
				{-1,  0,  0,  1, -4,  3,  4, -2,  0, -4, -3,  1, -2, -4, -2,  0, -1, -3, -3, -3,  0,  4, -1, -5},
				{-1, -1, -1, -2, -2, -1, -1, -2, -1, -2, -1, -1, -1, -2, -2, -1, -1, -3, -2, -1, -2, -1, -1, -5},
				{-5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5,  1},
		};

	public static final int[][] b80Matrix =
		{
				{ 7, -3, -3, -3, -1, -2, -2,  0, -3, -3, -3, -1, -2, -4, -1,  2,  0, -5, -4, -1, -3, -2, -1, -8},
				{-3,  9, -1, -3, -6,  1, -1, -4,  0, -5, -4,  3, -3, -5, -3, -2, -2, -5, -4, -4, -2,  0, -2, -8},
				{-3, -1,  9,  2, -5,  0, -1, -1,  1, -6, -6,  0, -4, -6, -4,  1,  0, -7, -4, -5,  5, -1, -2, -8},
				{-3, -3,  2, 10, -7, -1,  2, -3, -2, -7, -7, -2, -6, -6, -3, -1, -2, -8, -6, -6,  6,  1, -3, -8},
				{-1, -6, -5, -7, 13, -5, -7, -6, -7, -2, -3, -6, -3, -4, -6, -2, -2, -5, -5, -2, -6, -7, -4, -8},
				{-2,  1,  0, -1, -5,  9,  3, -4,  1, -5, -4,  2, -1, -5, -3, -1, -1, -4, -3, -4, -1,  5, -2, -8},
				{-2, -1, -1,  2, -7,  3,  8, -4,  0, -6, -6,  1, -4, -6, -2, -1, -2, -6, -5, -4,  1,  6, -2, -8},
				{ 0, -4, -1, -3, -6, -4, -4,  9, -4, -7, -7, -3, -5, -6, -5, -1, -3, -6, -6, -6, -2, -4, -3, -8},
				{-3,  0,  1, -2, -7,  1,  0, -4, 12, -6, -5, -1, -4, -2, -4, -2, -3, -4,  3, -5, -1,  0, -2, -8},
				{-3, -5, -6, -7, -2, -5, -6, -7, -6,  7,  2, -5,  2, -1, -5, -4, -2, -5, -3,  4, -6, -6, -2, -8},
				{-3, -4, -6, -7, -3, -4, -6, -7, -5,  2,  6, -4,  3,  0, -5, -4, -3, -4, -2,  1, -7, -5, -2, -8},
				{-1,  3,  0, -2, -6,  2,  1, -3, -1, -5, -4,  8, -3, -5, -2, -1, -1, -6, -4, -4, -1,  1, -2, -8},
				{-2, -3, -4, -6, -3, -1, -4, -5, -4,  2,  3, -3,  9,  0, -4, -3, -1, -3, -3,  1, -5, -3, -2, -8},
				{-4, -5, -6, -6, -4, -5, -6, -6, -2, -1,  0, -5,  0, 10, -6, -4, -4,  0,  4, -2, -6, -6, -3, -8},
				{-1, -3, -4, -3, -6, -3, -2, -5, -4, -5, -5, -2, -4, -6, 12, -2, -3, -7, -6, -4, -4, -2, -3, -8},
				{ 2, -2,  1, -1, -2, -1, -1, -1, -2, -4, -4, -1, -3, -4, -2,  7,  2, -6, -3, -3,  0, -1, -1, -8},
				{ 0, -2,  0, -2, -2, -1, -2, -3, -3, -2, -3, -1, -1, -4, -3,  2,  8, -5, -3,  0, -1, -2, -1, -8},
				{-5, -5, -7, -8, -5, -4, -6, -6, -4, -5, -4, -6, -3,  0, -7, -6, -5, 16,  3, -5, -8, -5, -5, -8},
				{-4, -4, -4, -6, -5, -3, -5, -6,  3, -3, -2, -4, -3,  4, -6, -3, -3,  3, 11, -3, -5, -4, -3, -8},
				{-1, -4, -5, -6, -2, -4, -4, -6, -5,  4,  1, -4,  1, -2, -4, -3,  0, -5, -3,  7, -6, -4, -2, -8},
				{-3, -2,  5,  6, -6, -1,  1, -2, -1, -6, -7, -1, -5, -6, -4,  0, -1, -8, -5, -6,  6,  0, -3, -8},
				{-2,  0, -1,  1, -7,  5,  6, -4,  0, -6, -5,  1, -3, -6, -2, -1, -2, -5, -4, -4,  0,  6, -1, -8},
				{-1, -2, -2, -3, -4, -2, -2, -3, -2, -2, -2, -2, -2, -3, -3, -1, -1, -5, -3, -2, -3, -1, -2, -8},
				{-8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8, -8,  1},
		};

	public static final int[][] b85Matrix =
		{
				{ 5, -2, -2, -2, -1, -1, -1,  0, -2, -2, -2, -1, -2, -3, -1,  1,  0, -3, -3, -1, -2, -1, -1, -6},
				{-2,  6, -1, -2, -4,  1, -1, -3,  0, -4, -3,  2, -2, -4, -2, -1, -2, -4, -3, -3, -2,  0, -2, -6},
				{-2, -1,  7,  1, -4,  0, -1, -1,  0, -4, -4,  0, -3, -4, -3,  0,  0, -5, -3, -4,  4, -1, -2, -6},
				{-2, -2,  1,  7, -5, -1,  1, -2, -2, -5, -5, -1, -4, -4, -2, -1, -2, -6, -4, -4,  4,  1, -2, -6},
				{-1, -4, -4, -5,  9, -4, -5, -4, -5, -2, -2, -4, -2, -3, -4, -2, -2, -4, -3, -1, -4, -5, -3, -6},
				{-1,  1,  0, -1, -4,  6,  2, -3,  1, -4, -3,  1,  0, -4, -2, -1, -1, -3, -2, -3, -1,  4, -1, -6},
				{-1, -1, -1,  1, -5,  2,  6, -3, -1, -4, -4,  0, -3, -4, -2, -1, -1, -4, -4, -3,  0,  4, -1, -6},
				{ 0, -3, -1, -2, -4, -3, -3,  6, -3, -5, -5, -2, -4, -4, -3, -1, -2, -4, -5, -4, -1, -3, -2, -6},
				{-2,  0,  0, -2, -5,  1, -1, -3,  8, -4, -3, -1, -3, -2, -3, -1, -2, -3,  2, -4, -1,  0, -2, -6},
				{-2, -4, -4, -5, -2, -4, -4, -5, -4,  5,  1, -3,  1, -1, -4, -3, -1, -3, -2,  3, -5, -4, -2, -6},
				{-2, -3, -4, -5, -2, -3, -4, -5, -3,  1,  4, -3,  2,  0, -4, -3, -2, -3, -2,  0, -5, -4, -2, -6},
				{-1,  2,  0, -1, -4,  1,  0, -2, -1, -3, -3,  6, -2, -4, -2, -1, -1, -5, -3, -3, -1,  1, -1, -6},
				{-2, -2, -3, -4, -2,  0, -3, -4, -3,  1,  2, -2,  7, -1, -3, -2, -1, -2, -2,  0, -4, -2, -1, -6},
				{-3, -4, -4, -4, -3, -4, -4, -4, -2, -1,  0, -4, -1,  7, -4, -3, -3,  0,  3, -1, -4, -4, -2, -6},
				{-1, -2, -3, -2, -4, -2, -2, -3, -3, -4, -4, -2, -3, -4,  8, -1, -2, -5, -4, -3, -3, -2, -2, -6},
				{ 1, -1,  0, -1, -2, -1, -1, -1, -1, -3, -3, -1, -2, -3, -1,  5,  1, -4, -2, -2,  0, -1, -1, -6},
				{ 0, -2,  0, -2, -2, -1, -1, -2, -2, -1, -2, -1, -1, -3, -2,  1,  5, -4, -2,  0, -1, -1, -1, -6},
				{-3, -4, -5, -6, -4, -3, -4, -4, -3, -3, -3, -5, -2,  0, -5, -4, -4, 11,  2, -3, -5, -4, -3, -6},
				{-3, -3, -3, -4, -3, -2, -4, -5,  2, -2, -2, -3, -2,  3, -4, -2, -2,  2,  7, -2, -4, -3, -2, -6},
				{-1, -3, -4, -4, -1, -3, -3, -4, -4,  3,  0, -3,  0, -1, -3, -2,  0, -3, -2,  5, -4, -3, -1, -6},
				{-2, -2,  4,  4, -4, -1,  0, -1, -1, -5, -5, -1, -4, -4, -3,  0, -1, -5, -4, -4,  4,  0, -2, -6},
				{-1,  0, -1,  1, -5,  4,  4, -3,  0, -4, -4,  1, -2, -4, -2, -1, -1, -4, -3, -3,  0,  4, -1, -6},
				{-1, -2, -2, -2, -3, -1, -1, -2, -2, -2, -2, -1, -1, -2, -2, -1, -1, -3, -2, -1, -2, -1, -2, -6},
				{-6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6,  1},
		};

	public static final int[][] b90Matrix =
		{
				{ 5, -2, -2, -3, -1, -1, -1,  0, -2, -2, -2, -1, -2, -3, -1,  1,  0, -4, -3, -1, -2, -1, -1, -6},
				{-2,  6, -1, -3, -5,  1, -1, -3,  0, -4, -3,  2, -2, -4, -3, -1, -2, -4, -3, -3, -2,  0, -2, -6},
				{-2, -1,  7,  1, -4,  0, -1, -1,  0, -4, -4,  0, -3, -4, -3,  0,  0, -5, -3, -4,  4, -1, -2, -6},
				{-3, -3,  1,  7, -5, -1,  1, -2, -2, -5, -5, -1, -4, -5, -3, -1, -2, -6, -4, -5,  4,  0, -2, -6},
				{-1, -5, -4, -5,  9, -4, -6, -4, -5, -2, -2, -4, -2, -3, -4, -2, -2, -4, -4, -2, -4, -5, -3, -6},
				{-1,  1,  0, -1, -4,  7,  2, -3,  1, -4, -3,  1,  0, -4, -2, -1, -1, -3, -3, -3, -1,  4, -1, -6},
				{-1, -1, -1,  1, -6,  2,  6, -3, -1, -4, -4,  0, -3, -5, -2, -1, -1, -5, -4, -3,  0,  4, -2, -6},
				{ 0, -3, -1, -2, -4, -3, -3,  6, -3, -5, -5, -2, -4, -5, -3, -1, -3, -4, -5, -5, -2, -3, -2, -6},
				{-2,  0,  0, -2, -5,  1, -1, -3,  8, -4, -4, -1, -3, -2, -3, -2, -2, -3,  1, -4, -1,  0, -2, -6},
				{-2, -4, -4, -5, -2, -4, -4, -5, -4,  5,  1, -4,  1, -1, -4, -3, -1, -4, -2,  3, -5, -4, -2, -6},
				{-2, -3, -4, -5, -2, -3, -4, -5, -4,  1,  5, -3,  2,  0, -4, -3, -2, -3, -2,  0, -5, -4, -2, -6},
				{-1,  2,  0, -1, -4,  1,  0, -2, -1, -4, -3,  6, -2, -4, -2, -1, -1, -5, -3, -3, -1,  1, -1, -6},
				{-2, -2, -3, -4, -2,  0, -3, -4, -3,  1,  2, -2,  7, -1, -3, -2, -1, -2, -2,  0, -4, -2, -1, -6},
				{-3, -4, -4, -5, -3, -4, -5, -5, -2, -1,  0, -4, -1,  7, -4, -3, -3,  0,  3, -2, -4, -4, -2, -6},
				{-1, -3, -3, -3, -4, -2, -2, -3, -3, -4, -4, -2, -3, -4,  8, -2, -2, -5, -4, -3, -3, -2, -2, -6},
				{ 1, -1,  0, -1, -2, -1, -1, -1, -2, -3, -3, -1, -2, -3, -2,  5,  1, -4, -3, -2,  0, -1, -1, -6},
				{ 0, -2,  0, -2, -2, -1, -1, -3, -2, -1, -2, -1, -1, -3, -2,  1,  6, -4, -2, -1, -1, -1, -1, -6},
				{-4, -4, -5, -6, -4, -3, -5, -4, -3, -4, -3, -5, -2,  0, -5, -4, -4, 11,  2, -3, -6, -4, -3, -6},
				{-3, -3, -3, -4, -4, -3, -4, -5,  1, -2, -2, -3, -2,  3, -4, -3, -2,  2,  8, -3, -4, -3, -2, -6},
				{-1, -3, -4, -5, -2, -3, -3, -5, -4,  3,  0, -3,  0, -2, -3, -2, -1, -3, -3,  5, -4, -3, -2, -6},
				{-2, -2,  4,  4, -4, -1,  0, -2, -1, -5, -5, -1, -4, -4, -3,  0, -1, -6, -4, -4,  4,  0, -2, -6},
				{-1,  0, -1,  0, -5,  4,  4, -3,  0, -4, -4,  1, -2, -4, -2, -1, -1, -4, -3, -3,  0,  4, -1, -6},
				{-1, -2, -2, -2, -3, -1, -2, -2, -2, -2, -2, -1, -1, -2, -2, -1, -1, -3, -2, -2, -2, -1, -2, -6},
				{-6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6, -6,  1},
		};

	public static final int[][] b100Matrix =
		{
				{ 8, -3, -4, -5, -2, -2, -3, -1, -4, -4, -4, -2, -3, -5, -2,  1, -1, -6, -5, -2, -4, -2, -2, -10},
				{-3, 10, -2, -5, -8,  0, -2, -6, -1, -7, -6,  3, -4, -6, -5, -3, -3, -7, -5, -6, -4, -1, -3, -10},
				{-4, -2, 11,  1, -5, -1, -2, -2,  0, -7, -7, -1, -5, -7, -5,  0, -1, -8, -5, -7,  5, -2, -3, -10},
				{-5, -5,  1, 10, -8, -2,  2, -4, -3, -8, -8, -3, -8, -8, -5, -2, -4, -10, -7, -8,  6,  0, -4, -10},
				{-2, -8, -5, -8, 14, -7, -9, -7, -8, -3, -5, -8, -4, -4, -8, -3, -3, -7, -6, -3, -7, -8, -5, -10},
				{-2,  0, -1, -2, -7, 11,  2, -5,  1, -6, -5,  2, -2, -6, -4, -2, -3, -5, -4, -5, -2,  5, -2, -10},
				{-3, -2, -2,  2, -9,  2, 10, -6, -2, -7, -7,  0, -5, -8, -4, -2, -3, -8, -7, -5,  0,  7, -3, -10},
				{-1, -6, -2, -4, -7, -5, -6,  9, -6, -9, -8, -5, -7, -8, -6, -2, -5, -7, -8, -8, -3, -5, -4, -10},
				{-4, -1,  0, -3, -8,  1, -2, -6, 13, -7, -6, -3, -5, -4, -5, -3, -4, -5,  1, -7, -2, -1, -4, -10},
				{-4, -7, -7, -8, -3, -6, -7, -9, -7,  8,  2, -6,  1, -2, -7, -5, -3, -6, -4,  4, -8, -7, -3, -10},
				{-4, -6, -7, -8, -5, -5, -7, -8, -6,  2,  8, -6,  3,  0, -7, -6, -4, -5, -4,  0, -8, -6, -3, -10},
				{-2,  3, -1, -3, -8,  2,  0, -5, -3, -6, -6, 10, -4, -6, -3, -2, -3, -8, -5, -5, -2,  0, -3, -10},
				{-3, -4, -5, -8, -4, -2, -5, -7, -5,  1,  3, -4, 12, -1, -5, -4, -2, -4, -5,  0, -7, -4, -3, -10},
				{-5, -6, -7, -8, -4, -6, -8, -8, -4, -2,  0, -6, -1, 11, -7, -5, -5,  0,  4, -3, -7, -7, -4, -10},
				{-2, -5, -5, -5, -8, -4, -4, -6, -5, -7, -7, -3, -5, -7, 12, -3, -4, -8, -7, -6, -5, -4, -4, -10},
				{ 1, -3,  0, -2, -3, -2, -2, -2, -3, -5, -6, -2, -4, -5, -3,  9,  2, -7, -5, -4, -1, -2, -2, -10},
				{-1, -3, -1, -4, -3, -3, -3, -5, -4, -3, -4, -3, -2, -5, -4,  2,  9, -7, -5, -1, -2, -3, -2, -10},
				{-6, -7, -8, -10, -7, -5, -8, -7, -5, -6, -5, -8, -4,  0, -8, -7, -7, 17,  2, -5, -9, -7, -6, -10},
				{-5, -5, -5, -7, -6, -4, -7, -8,  1, -4, -4, -5, -5,  4, -7, -5, -5,  2, 12, -5, -6, -6, -4, -10},
				{-2, -6, -7, -8, -3, -5, -5, -8, -7,  4,  0, -5,  0, -3, -6, -4, -1, -5, -5,  8, -7, -5, -3, -10},
				{-4, -4,  5,  6, -7, -2,  0, -3, -2, -8, -8, -2, -7, -7, -5, -1, -2, -9, -6, -7,  6,  0, -4, -10},
				{-2, -1, -2,  0, -8,  5,  7, -5, -1, -7, -6,  0, -4, -7, -4, -2, -3, -7, -6, -5,  0,  6, -2, -10},
				{-2, -3, -3, -4, -5, -2, -3, -4, -4, -3, -3, -3, -3, -4, -4, -2, -2, -6, -4, -3, -4, -2, -3, -10},
				{-10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10, -10,  1},
		};

	/**
	 * 
	 * @param bNum number of the Blosum matrix (e.g. 50, 62)
	 * @param a1 first amino acid
	 * @param a2 second amino acid
	 * @return the (log) likelihood of the amino acids being swapped in multiple sequence alignments of proteins with bNum% similarity.
	 */
	public static int getValue(int bNum, char a1, char a2) {
		// TODO
		return 0;
	}

	/**
	 * returns the row of the corresponding amino acid
	 * @param aa Amino Acid
	 * @return the values of the row/column for that Amino Acid
	 */
	public static int[] getRow(AminoAcid aa, BlosumNum bNum) {
		return getMatrix(bNum)[aaToIndex(aa)];
	}

	public static int[][] getMatrix(BlosumNum bNum) {
		switch(bNum) {
		case b30:
			return b30Matrix;
		case b35:
			return b35Matrix;
		case b40:
			return b40Matrix;
		case b45:
			return b45Matrix;
		case b50:
			return b50Matrix;
		case b55:
			return b55Matrix;
		case b60:
			return b60Matrix;
		case b62:
			return b62Matrix;
		case b65:
			return b65Matrix;
		case b70:
			return b70Matrix;
		case b75:
			return b75Matrix;
		case b80:
			return b80Matrix;
		case b85:
			return b85Matrix;
		case b90:
			return b90Matrix;
		case b100:
			return b100Matrix;
		default:
			return b50Matrix;
		}
	}
	
	public static int aaToIndex(AminoAcid aa) {
		return AAOrder.toString().indexOf(AAInterface.aaToChar(aa));
	}
}
