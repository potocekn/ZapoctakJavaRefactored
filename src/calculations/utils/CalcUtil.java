package calculations.utils;

import java.util.HashMap;
import java.util.Map;

/**This class contains methods used for calculating new data from given data.*/
public final class CalcUtil {

    /**This method returns sum of given arrays (addition by values).
     * @param arr is the array we would like to add
     * @param constant is the constant that we should add the array with.*/
    public double[] addArrayConstant(double[] arr, double constant)
    {
        double[] result = new double[arr.length];
        for (int i = 0; i < arr.length; i++)
        {
            result[i] = arr[i] + constant;
        }
        return result;
    }

    /**This method returns sum of given arrays.
     * @param arr1 represents the first array
     * @param arr2 represents the second array*/
    public double[] addArrays(double[] arr1, double[] arr2)
    {
        double[] result = new double[Math.max(arr1.length,arr2.length)];
        for (int i = 0; i < result.length; i++)
        {
            if ((arr1.length > i)&&(arr2.length > i))
            {
                result[i] = arr1[i] + arr2[i];
            }
            else if ((arr1.length > i))
            {
                result[i] = arr1[i];
            }
            else if ((arr2.length > i))
            {
                result[i] = arr2[i];
            }
        }
        return result;
    }

    /**This method returns array with yearly inflation influenced by inflation in previous years.
     * @param inflation is array of expected inflation for each year*/
    public double[] inflationMultiply(double[] inflation)
    {
        double[] result = new double[inflation.length];
        result[0] = 1 + inflation[0];
        for (int i = 1; i<inflation.length;i++)
        {
            result[i] = result[i-1]*(1+inflation[i]);
        }
        return result;
    }

    /**This method returns product of given arrays.
     * @param arr1 represents first array
     * @param arr2 represents second array*/
    public double[] multiplyArrays(double[] arr1, double[] arr2)
    {
        double[] result = new double[Math.max(arr1.length,arr2.length)];
        for (int i = 0; i < result.length; i++)
        {
            if ((arr1.length > i)&&(arr2.length > i))
            {
                result[i] = arr1[i]*arr2[i];
            }
            else if ((arr1.length > i))
            {
                result[i] = arr1[i];
            }
            else if ((arr2.length > i))
            {
                result[i] = arr2[i];
            }
        }
        return result;
    }

    /**This method returns array of quotients of values in given arrays.
     * @param arr1 represents first array
     * @param arr2 represents second array*/
    public double[] divideArrays(double[] arr1, double[] arr2)
    {
        double[] result = new double[Math.max(arr1.length,arr2.length)];
        for (int i = 0; i < result.length; i++)
        {
            if ((arr1.length > i)&&(arr2.length > i))
            {
                if (arr2[i] != 0)
                {
                    result[i] = arr1[i]/arr2[i];
                }
                else
                {
                    result[i] = 0;
                }

            }
            else if ((arr1.length > i))
            {
                result[i] = arr1[i];
            }
            else if ((arr2.length > i))
            {
                result[i] = arr2[i];
            }
        }
        return result;
    }

    /**This method returns product of given array and double constant (multiplication).
     * @param arr represents first array
     * @param ratio represents ratio constant as double value*/
    public double[] multiply(double[] arr, double ratio)
    {
        double[] result = new double[arr.length];
        for (int i = 0; i < arr.length; i++)
        {
            result[i] = arr[i]*ratio;
        }
        return result;
    }

    /**This method returns product of given array and integer constant (multiplication).
     * @param arr represents the array
     * @param ratio represents integer constant the array is musltiplied by*/
    public double[] multiply(double[] arr, int ratio)
    {
        double[] result = new double[arr.length];
        for (int i = 0; i < arr.length; i++)
        {
            result[i] = arr[i]*ratio;
        }
        return result;
    }

    /**This method returns product of values in given map and given ratios.
     * @param map represents the map we would like to multiply
     * @param ratios represents the array of ratios for multiplication*/
    public Map<String, double[]> multiplyMap(Map<String, double[]> map, double[] ratios)
    {
        Map<String, double[]> result = new HashMap<>();
        for (String key:map.keySet())
        {
            double[] values = new double[ratios.length];
            for (int i = 0; i < ratios.length; i++)
            {
                values[i] = ratios[i]*map.get(key)[i];
            }
            result.put(key, values);
        }
        return result;
    }
}
